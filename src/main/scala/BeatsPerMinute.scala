import scala.util.Random

class BeatsPerMinute {
  val BPM_DEFAULT = 120
  var currentBPM = BPM_DEFAULT

  def built():String ={
    return s"T$currentBPM "
  }

  def increase():String={
    currentBPM+=80
    return built()
  }

  def random():String={
    currentBPM = Random.between(1,221)
    return built()
  }
}
