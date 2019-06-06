/**
  Represent a square chess board for a queens puzzle
  of a particular size.
 */

public class BoardForQueensPuzzle {
    private int lastRankFilled = -1;  /* the highest-numbered rank
      that holds a queen. */
    private int[] filesWithQueens;  /* indexed by rank, giving
      the file number that a queen occupies in that rank.
      Interacts with lastRankFilled: entries in filesWithQueens
      are meaningful only for ranks <= lastRankFilled. Entries
      at higher indexes hold bit patterns that can be interpreted
      as integers, as always. But those entries are meaningless.
      This common thinking was used for the filledElements field
      in List_inArraySlots exactly the same way.
      */


    /**
      Construct an empty instance of the specified size
     */
    public BoardForQueensPuzzle( int ranks) {
      filesWithQueens = new int[ranks];
    }


    /**
      @return the size of the board
     */
    public int ranks() {
      return filesWithQueens.length;   
    }


    /**
      @return the boolean value of the statement
              "The last-added queen introduced a conflict."

      @precondition: Before the latest queen was added,
                     no queen attacked another.
     */
    public boolean lastIsNg() {
      for(int index = 0; index < lastRankFilled; index++){
        if (filesWithQueens[index] == filesWithQueens[lastRankFilled])
          return true;
        if (index + 1 < filesWithQueens.length){
          if (Math.abs( filesWithQueens[index + 1] - filesWithQueens[index]) == 1)
            return true;
        }
          if (index - 1 >= 0){
            if (Math.abs( filesWithQueens[index - 1] - filesWithQueens[index]) == 1)
          return true;
      }
    }
      return false;
    }


    /**
      @return the boolean value of the statement...
                "The board is fully and legally populated."

      precondition: All ranks except possibly the last-filled
        are populated legally. (That is, considering only the
        ranks before the last-filled rank, no queen attacks
        another.

        This method checks the last-filled rank.
     */
    public boolean accept() {
      if (filesWithQueens.length == 0)
        return true; //optional education
      if (filesWithQueens.length == 1 && lastRankFilled == 0)
        return true;
      for(int index = 0; index < lastRankFilled; index++){
        if (filesWithQueens[lastRankFilled] == filesWithQueens[index]){
          return false;
        }
        if (index + 1 < filesWithQueens.length){
          if (Math.abs( filesWithQueens[index + 1] - filesWithQueens[index]) == 1)
            return false;
        }
          if (index - 1 >= 0){
            if (Math.abs( filesWithQueens[index - 1] - filesWithQueens[index]) == 1)
          return false;
      }
    }
      if (lastRankFilled < 0 || lastRankFilled != filesWithQueens.length - 1)
          return false;
        return true;
    }


    /**
      Populate the next rank with a queen in position @file
     */
    public void populate( int file) {
      filesWithQueens[++lastRankFilled] = file;
    }


    /**
      Un-do a populate(), to have no queen in the
      most-recently-populated rank.
      @precondition: Some rank(s) have been populated.
     */
    public void depopulate() {
      filesWithQueens[lastRankFilled--] = -1;
    }


    // ----- skeletal code below here needs no modification -----
    private static final String INDENT = "         ";
    /**
      @return a string representation of this board
     */
    public String toString() {
        int size = filesWithQueens.length; // just for shorthand

        /* Make a header containing file numbers like...
               3 ranks, populated through rank 1
               files
               0  1  2  3 ...
               __________ ...
         */

        // sizes and labels that precede the ranks
        String pic = INDENT
          + size + " ranks, "
          + ( // ?: conditional operator
                lastRankFilled < 0
              ? "unpopulated"
              : "populated through rank " + lastRankFilled
            )
          + System.lineSeparator();

        // short-circuit the picture for a zero-sized board
        if( size == 0) return pic;

        pic += INDENT + "files" + System.lineSeparator()
            +  INDENT;
        String underscores = "";

        // file numbers, each in a 3-column field
        for( int file = 0; file < size; file++) {
            pic += String.format("%-3d", file);
            underscores += "___"; // there's gotta be a better way
        }

        // underline the header
        pic+=   System.lineSeparator()
              + INDENT + underscores + System.lineSeparator();

        // picture each rank
        for( int rank = 0; rank < size; rank++){
            pic += // right-justified rank number:
                   String.format("rank %2d|", rank);
            for( int file = 0; file < size; file++)
                if(    rank > lastRankFilled      // no queen in this rank yet
                    || filesWithQueens[ rank] != file) // no queen in this file
                     pic += " _ ";
                else pic += " Q ";
            pic += System.lineSeparator();
        }
        return pic;
    }
}