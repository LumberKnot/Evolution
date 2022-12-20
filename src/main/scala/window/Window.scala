package window

import controller.{Position, Radians, Transform}

import java.awt.event.{KeyEvent, KeyListener, MouseEvent, MouseListener, MouseMotionListener, MouseWheelListener, MouseWheelEvent}
import java.awt.Graphics2D
import java.awt.Color
case class Window(val title: String, val width: Int, val height: Int):
  val keyManager = new KeyManager()
  private val mouseManager = new MouseManager()
  private val display: Display = new Display(title, width, height)
  private var camera = Transform(0, 0, 0)
  private var _scale: Double = 1;
  //TODO ENCAPSULATE BELLOW ALSO DO I REALLY NEED BOTH?!?
  display.frame.addKeyListener(keyManager)
  display.frame.addMouseListener(mouseManager)
  display.frame.addMouseMotionListener(mouseManager)
  display.frame.addMouseWheelListener(mouseManager)
  display.canvas.addMouseListener(mouseManager)
  display.canvas.addMouseMotionListener(mouseManager)
  display.canvas.addMouseWheelListener(mouseManager)
  def setScale(scale: Double): Unit =
    assert(scale != 0, "Scale set to 0, will cause divison by zero error")
    _scale = scale

  def scale(scalar: Double): Unit =
    assert(scalar != 0, "Cant be 0")
    _scale *= scalar
  def setOrigin(position: Position): Unit =
  camera = camera.moveTo(position)

  def setRotation(rotation: Radians): Unit =
  camera = camera.rotateTo(rotation)

  def leftPressed: Boolean = mouseManager.leftPressed
  def rightPressed: Boolean = mouseManager.rightPressed
  def scrollForward: Boolean = mouseManager.scrollForward
  def scrollBackwards: Boolean = mouseManager.scrollBackwards

  def mousePos: Position =
    val offsetPos = (camera.position - Position.fromIntTuple(mouseManager.pos))
    offsetPos / _scale


  def render(draw: Graphics2D => Unit): Unit =
    var bs = display.canvas.getBufferStrategy
    if bs == null then
      display.canvas.createBufferStrategy(3)
      bs = display.canvas.getBufferStrategy

    val g2d = bs.getDrawGraphics().asInstanceOf[Graphics2D]
    val (x: Int, y: Int) = camera.position.getTuple
    g2d.clearRect(0, 0, width, height)
    g2d.translate(x, y)
    g2d.scale(_scale, _scale)






    draw(g2d) //RITAR ALLT
    bs.show()
    g2d.dispose()




class KeyManager() extends KeyListener:
  private val keys: Array[Boolean] = new Array(526)

  override def keyPressed(e: KeyEvent): Unit =
    keys(e.getKeyCode) = true
    if(e.getKeyCode == 27)
      System.exit(0)

  override def keyReleased(e: KeyEvent): Unit =
    keys(e.getKeyCode) = false

  override def keyTyped(e: KeyEvent): Unit = ()


    //Ingen anledning att ha atm :)

  def isKeyPressed(input: Int): Boolean =
    keys(input)

  def lookUpKey(table: Map[String, Int], input: String): Boolean =
    isKeyPressed(table(input))




class MouseManager() extends MouseListener, MouseMotionListener, MouseWheelListener:
  var leftPressed: Boolean = false
  var rightPressed: Boolean = false
  var scrollForward: Boolean = false
  var scrollBackwards: Boolean = false
  var pos: (Int, Int) = (-1, -1)

  override def mouseWheelMoved(e: MouseWheelEvent): Unit =
      scrollForward = e.getWheelRotation() == -1
      scrollBackwards = e.getWheelRotation() == 1


  override def mouseClicked(e: MouseEvent): Unit = {}
  override def mouseEntered(e: MouseEvent): Unit = {}
  override def mouseExited(e: MouseEvent): Unit = {}

  override def mousePressed(e: MouseEvent): Unit =
    println("PRESSED")
    if (e.getButton() == MouseEvent.BUTTON1) then leftPressed = true
    else if (e.getButton() == MouseEvent.BUTTON2) then rightPressed = true

  override def mouseReleased(e: MouseEvent): Unit =
    if (e.getButton() == MouseEvent.BUTTON1) then leftPressed = false
    if (e.getButton() == MouseEvent.BUTTON2) then rightPressed = false

  override def mouseDragged(e: MouseEvent): Unit =
    pos = (e.getX(), e.getY())
  override def mouseMoved(e: MouseEvent): Unit =
    pos = (e.getX(), e.getY())


class Display(title: String, var width: Int, var height: Int):
  import java.awt.Canvas
  import java.awt.Dimension
  import javax.swing.JFrame
  val frame = new JFrame(title)
  frame.setSize(width, height)
  frame.setDefaultCloseOperation(3)
  frame.setResizable(false)
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)

  val canvas = new Canvas()

  //canvas.setBackground(new java.awt.Color(255, 0, 0))
  canvas.setPreferredSize(new Dimension(width, height))
  canvas.setMaximumSize(new Dimension(width, height))
  canvas.setMinimumSize(new Dimension(width, height))
  canvas.setFocusable(false)
  frame.add(canvas)
  frame.pack()
