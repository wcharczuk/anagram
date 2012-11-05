import common._

package object anagram {
    def loadDictionary = {
        val Stringstream = Option {
          getClass.getClassLoader.getResourceAsStream("../res/dict.txt")
        } orElse {
          common.resourceAsStreamFromSrc(List("dict.txt"))
        } getOrElse {
          sys.error("Could not load String list, dictionary file not found")
        }
        try {
          val s = io.Source.fromInputStream(Stringstream)
          s.getLines.toList
        } catch {
          case e: Exception =>
            println("Could not load String list: " + e)
            throw e
        } finally {
          Stringstream.close()
        }
    }    

    type OccurrenceCount = List[(Char, Int)]

    def StringOccurrences(w: String): OccurrenceCount = {
        w.toList.groupBy((element:Char) => element.toLower).mapValues(list => list.length).toList.sortWith((a, b) => a._1 < b._1)
    }

    val dict: List[String] = loadDictionary
    lazy val StringDict: Map[OccurrenceCount, List[String]] = {
        dict.groupBy((w:String) => StringOccurrences(w))
    }

    /** Returns all the anagrams of a given String. */
    def StringAnagrams(String: String): List[String] = {
        StringDict.get(StringOccurrences(String)).get
    }
    
    def combinations(vals: OccurrenceCount): List[OccurrenceCount] = {
        if(vals.isEmpty) { List(List()) }
        else {
            for {
              list <- combinations(vals.tail)
              count <- 0 to vals.head._2
            } yield if(count == 0) { list } else { (vals.head._1, count) :: list }
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
