object Hello {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 
  println("Welcome to the Scala worksheet");$skip(29); 
  
  val d3 = math.pow(3, 2);System.out.println("""d3  : Double = """ + $show(d3 ));$skip(12); val res$0 = 
  
  d3 / 3;System.out.println("""res0: Double = """ + $show(res$0));$skip(17); val res$1 = 
  
  "carzy" * 3;System.out.println("""res1: String = """ + $show(res$1));$skip(14); val res$2 = 
  
  10 max 2;System.out.println("""res2: Int = """ + $show(res$2))}
  
}
