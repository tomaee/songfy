import scala.util.Random
class PatternBuilder {
  var volumeBuilder = new Volume
  var noteBuilder = new Note
  var octaveBuilder = new Octave
  var instrumetBuilder = new Instrument
  var BPMBuilder = new BeatsPerMinute

  def build(txt:String):String = {
    val rest = "R "
    val toChangeNote = List('A','B','C','D','E','F','G','a','b','c','d','e','f','g')
    val toChangeInstrument = List('0','1','2','3','4','5','6','7','8','9')
    val toRepeateOrRing = List('O','I','U','o','i','u')
    val toRest = List(' ')
    var song = s"${volumeBuilder.reset()} ${BPMBuilder.built()} ${instrumetBuilder.buildInstrument()} "


    var charIndex = 0
    var txtSize = txt.length()-1

    while(charIndex <= txtSize){
      var currentChar = txt.charAt(charIndex)

      if(currentChar == 'B' && charIndex <= txtSize-3){
        val isP = txt.charAt(charIndex+1) == 'P'
        val isM = txt.charAt(charIndex+2) == 'M'
        val isPlus = txt.charAt(charIndex+3) == '+'
        if(isP && isM && isPlus){
          song += BPMBuilder.increase()
          charIndex += 3
        }
      }

      else if(currentChar == ';')
        song += BPMBuilder.random()

      else if(currentChar == 'R' && charIndex <= txtSize-1){
        var nextChar = txt.charAt(currentChar+1)
        if(nextChar == '+'){
          charIndex+=1
          octaveBuilder.increase()
        }
        if(nextChar == '-'){
          charIndex+=1
          octaveBuilder.decrease()
        }
      }

      else if(toChangeNote.contains(currentChar)){
        var newNote = noteBuilder.build(currentChar)
        var octave = octaveBuilder.octave
        song += s"${newNote}${octave} "
      }

      else if(toRepeateOrRing.contains(currentChar)){
        var lastChar = txt.charAt(charIndex - 1)

        if (toChangeNote.contains(lastChar)){
          var newNote = noteBuilder.build(lastChar)
          var octave = octaveBuilder.octave
          song += s"${newNote}${octave} "
        }else{
          song += instrumetBuilder.usePhoneRing()
        }
      }

      else if(currentChar == '?'){
        var randIndex = Random.between(0, toChangeNote.length)
        var newNote = noteBuilder.build(toChangeNote.apply(randIndex))
        var octave = octaveBuilder.octave
        song += s"${newNote}${octave} "
      }

      else if(currentChar == '+')
        song += volumeBuilder.increase()

      else if(currentChar == '-')
        song += volumeBuilder.reset()

      else if(currentChar == '\n')
        song += instrumetBuilder.useAcousticGuitar()

      else if(currentChar == ' ')
        song += rest

      else if(currentChar == '!')
        song += instrumetBuilder.useAgogo()

      else if(toChangeInstrument.contains(currentChar))
        song += instrumetBuilder.buildInstrument(currentChar.toInt)

      charIndex += 1
    }
    return song
  }
}
