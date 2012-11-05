import common._
import anagram._
import org.clapper.argot._
import org.clapper.argot.ArgotConverters._


object AnagramSolver {
    val parser = new ArgotParser(
        "anagram",
        preUsage=Some("Anagram Solver: Version 1.0 Copyright (c) 2012 Will Charczuk"),
        compactUsage=true
    )
    
    val inputWord      = parser.parameter[String]("word", "The letter collection to permute", false)
    val minLength      = parser.option[Int](List("m", "minlength"), "n", "Minimum length for a result permutation.")
    val maxResults     = parser.option[Int](List("r", "results"), "n", "Maximum number of results to print.")
    
    def main(args:Array[String]) = {
        try
        {
            parser.parse(args)
            
            println("Generating Anagrams for ::> " + inputWord)
            
            var minLengthImpl = 3
            if(minLength.hasValue && !minLength.value.isEmpty)
                minLengthImpl = minLength.value.get
            
            if(maxResults.hasValue && !maxResults.value.isEmpty)
            {
                println("Limiting Results to: " + maxResults.value.get)
                for(result <- StringAnagrams(inputWord.value.get, minLengthImpl).sortWith((a,b) => a.length > b.length).take(maxResults.value.get)) {
                    println(result)
                }
            }
            else
            {
                for(result <- StringAnagrams(inputWord.value.get, minLengthImpl).sortWith((a,b) => a.length > b.length)) {
                    println(result)
                }
            }
        }
        catch {
            case e: ArgotUsageException => println(e.message)
        }
    }
}