import scala.swing._
import scala.swing.event._

class View extends MainFrame {
  val builder = new PatternBuilder
  val player = new Player
  val textArea = new TextArea(){
    lineWrap = true
    wordWrap = true
  }
  listenTo(textArea.keys)
  reactions += {
    case KeyPressed(_,c,_,_) => 
      playLastChar()
  }
  
  val informationArea = new TextArea(){
    columns = 20
    lineWrap = true
    wordWrap = true
    editable = false
    text = """
  * A ou a - nota lá
  * B ou b - nota si
  * C ou c - nota dó
  * D ou d - nota ré
  * E ou e - nota mi
  * F ou f - nota fá
  * G ou g - nota sol
  * " " (espaço) - silêncio
  * "+" - dobra volume
  * "-" - reseta volume
  * Vogais extras - repete nota ou escolhe telefone
  * "R+" - aumenta oitava
  * "R-" - diminui outava
  * "?" - nota aleatória
  * "\n" (nova linha) - escolhe violão acústico
  * "BPM+" - aumenta o BPM
  * ";" - BPM aleatório
  * Qualquer outro padrão é ignorado
"""
  }
  // val btnSaveText = new Button("Salvar texto")

  val horizontalSpace = (value:Int) => Swing.HStrut(value)
  val verticalSpace = (value:Int) => Swing.VStrut(value)

  visible = true
  preferredSize = new Dimension(756, 512)
  contents = new BorderPanel {
    border = Swing.EmptyBorder(10, 10, 10, 10)
    add(new FlowPanel() {
      contents += Button("Abrir arquivo") { loadFile() }
      contents += horizontalSpace(5)
      contents += Button("Salvar MIDI") { saveMidi() }
    }, BorderPanel.Position.North)
    add(new ScrollPane(informationArea), BorderPanel.Position.West)
    add(new ScrollPane(textArea), BorderPanel.Position.Center)
    add(new FlowPanel() {
      contents += Button("Play"){ playSong() }
    }, BorderPanel.Position.South)
  }
  

  def playSong() {
    val pattern = builder.build(textArea.text)
      
    player.play(pattern)
  }

  def playLastChar() {
    val lastChar =  textArea.text.takeRight(1)

    val pattern = builder.build(lastChar)
    player.play(pattern)
  }

  def loadFile() {
    val fileHelper = new FileHelper
    val file = fileHelper.chooseFile()
    val txt = fileHelper.loadText(file)
    textArea.text = txt
  }

  def saveMidi() {
    val pattern = builder.build(textArea.text)
    val fileHelper = new FileHelper
    val path = fileHelper.chooseSavePath()

    player.saveMidi(pattern, path)
  }
}
