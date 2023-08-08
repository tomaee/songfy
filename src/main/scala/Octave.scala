class Octave {
  val DEFAULT_OCTAVE = 5
  var octave = DEFAULT_OCTAVE

  def reset_new(){
    octave = DEFAULT_OCTAVE
  }

  def increase(){
    octave += 1
    if(octave > 9) reset_new()
  }

  def decrease(){
    octave -= 1
    if(octave < 1) reset_new()
  }
}
