class Instrument {
  var currentInstrument = 1
  val churchOrgan = 20
  val panFlute = 76
  val tubularBells = 15
  val harpisichord = 7
  val agogo = 113
  val phoneRing = 125
  val acousticGuitar = 25

  def buildInstrument(midiNumber:Int=1):String = {
    currentInstrument = midiNumber
    return s"I[$midiNumber] "
  }

  def increaseInstrument(increment:Int):String = {
    return buildInstrument(currentInstrument + increment)
  }

  def useHarpisichord():String = {
    return buildInstrument(harpisichord)
  }

  def useTubularBells():String = {
    return buildInstrument(tubularBells)
  }

  def useChurchOrgan():String = {
    return buildInstrument(churchOrgan)
  }

  def usePanFlute():String = {
    return buildInstrument(panFlute)
  }

  def useAgogo():String = {
    return buildInstrument(agogo)
  }

  def usePhoneRing():String = {
    return buildInstrument(phoneRing)
  }

  def useAcousticGuitar():String = {
    return buildInstrument(acousticGuitar)
  }
}
