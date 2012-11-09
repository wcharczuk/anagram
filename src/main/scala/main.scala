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
    val mustInclude    = parser.option[String](List("i", "include"), "abc", "Permutation must contain 'a' and 'b' and 'c'")
    
    def main(args:Array[String]) = {
        try
        {
            parser.parse(args)
            var minLengthImpl = 3
            if(minLength.hasValue && !minLength.value.isEmpty)
                minLengthImpl = minLength.value.get
            
            var resultSet = StringAnagrams(inputWord.value.get, minLengthImpl).sortWith((a,b) => a.length > b.length);
            
            if(mustInclude.hasValue && !mustInclude.value.isEmpty)
            {
                val subSet = mustInclude.value.get
                resultSet = resultSet.filter(superSet => HasSubset(superSet, subSet))                
            }
            
            if(maxResults.hasValue && !maxResults.value.isEmpty)
            {
                resultSet = resultSet.take(maxResults.value.get)
            }
            
            for(result <- resultSet) {
                println(result)
            }
        }
        catch {
            case e: ArgotUsageException => println(e.message)
        }
    }
}