import common._
import anagram._

object AnagramSolver {
    def main(args:Array[String]) ={
        if(args.isEmpty)
            println("Usage: anagram <your_word> <min_length>")
            
        val inputWord = args(0)
        var minLength = 3
        
        if(args.length > 1)
            minLength = args(1).toInt
            
        println("Generating Anagrams for ::> " + inputWord)
        for(result <- StringAnagrams(inputWord, minLength).sortWith((a,b) => a.length > b.length).take(10)) {
            println(result)
        }
   }
}