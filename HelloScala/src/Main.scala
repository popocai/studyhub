

object Main {
  println("I will execute before main")
  
  
  //Tuple
  val pair = (100, "Scala", 12.3)
  println(pair._1)
  println(pair._2)
  println(pair._3)
  
  // Array
  val arr = Array(1,2,3,4,5);
  println(arr)
  
  val map = Map("T"->"A")
  println(map)
  
  def main(args: Array[String]): Unit = {
    println("Hello Scala");
    
    //doWhile();
    
    val text = if(!args.isEmpty) args(0) else "Default";
    println("text:" + text);
    
    for( i <- 1 to 3){
      println("Number is " + i)
    }
  }
  
  def doWhile(){
    var line = ""
    do{
      line = readLine();
      println(line)
    } while(line != "")
    
  }
  
  
  println("I will execute after main")
}