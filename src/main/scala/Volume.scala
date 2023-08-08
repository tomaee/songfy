class Volume {
  val VOLUME_DEFAULT = 4000
  var volume = VOLUME_DEFAULT

  def buildVolumePattern(v:Int):String={
    return s"X[Volume]=$v "
  }

  def reset():String = {
    volume = VOLUME_DEFAULT
    return buildVolumePattern(volume)
  }

  def increase():String = {
    volume = volume * 2
    return buildVolumePattern(volume)
  }
}
