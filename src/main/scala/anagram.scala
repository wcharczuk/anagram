//import scala.collection.mutable._

package object anagram {
    def loadDictionary = {
        val stringStream = Option {
          getClass.getClassLoader.getResourceAsStream("dict.txt")
        } getOrElse {
            sys.error("Could not load String list, dictionary file not found")
        }
        try {
            val s = io.Source.fromInputStream(stringStream)
            s.getLines.toList
        } catch { case e: Exception =>
            println("Could not load String list: " + e)
            throw e
        } finally {
            stringStream.close()
        }
    }    
    
    /* Computes the number of times each character occurs in a word. */
    def StringOccurrences(w: String): Map[Char, Int] = {
        w.toList
            .groupBy((element:Char) => element.toLower)
            .mapValues(list => list.length)
    }

    val dictionaryWords: List[String] = loadDictionary
    lazy val StringDict: Map[Map[Char, Int], List[String]] = {
        dictionaryWords
            .groupBy(
                (w:String) => StringOccurrences(w)
            )
    }

    /** Returns all the anagrams of a given String. */
    def StringAnagrams(String: String): List[String] = {
        StringDict.get(StringOccurrences(String)).get
    }
    
    def combinations(vals: Map[Char, Int]): List[Map[Char, Int]] = {
        if(vals.isEmpty) { List(Map()) }
        else {
            for {
              list <- combinations(vals.tail)
              count <- 0 to vals.head._2
            } yield if(count == 0) { list } else { list + (vals.head._1 -> count) }
        }
    }
    
    def StringAnagrams(input:String, minLength: Int = 3) : List[String] = {
        if(input.isEmpty) { List(List()) }
        
        for {
            c <- combinations(StringOccurrences(input))
            if StringDict.contains(c)
            w <- StringDict.get(c).get
            if w.length >= minLength
        } yield w
    }
}
