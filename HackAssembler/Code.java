public class Code {
  public String dest(String destInstruction) {
      if (destInstruction == null)
          return "000";
      if (destInstruction.equals("M"))
          return "001";
      if (destInstruction.equals("D"))
          return "010";
      if (destInstruction.equals("DM") || destInstruction.equals("MD"))
          return "011";
      if (destInstruction.equals("A"))
          return "100";
      if (destInstruction.equals("AM"))
          return "101";
      if (destInstruction.equals("AD"))
          return "110";
      if (destInstruction.equals("ADM"))
          return "111";

      return null;
  }

  public String comp(String compInstruction) {
      if (compInstruction.equals("0"))
          return "0101010";
      if (compInstruction.equals("1"))
          return "0111111";
      if (compInstruction.equals("-1"))
          return "0111010";
      if (compInstruction.equals("D"))
          return "0001100";
      if (compInstruction.equals("A"))
          return "0110000";
      if (compInstruction.equals("!D"))
          return "0001101";
      if (compInstruction.equals("!A"))
          return "0110001";
      if (compInstruction.equals("-D"))
          return "0001111";
      if (compInstruction.equals("-A"))
          return "0110011";
      if (compInstruction.equals("D+1"))
          return "0011111";
      if (compInstruction.equals("A+1"))
          return "0110111";
      if (compInstruction.equals("D-1"))
          return "0001110";
      if (compInstruction.equals("A-1"))
          return "0110010";
      if (compInstruction.equals("D+A"))
          return "0000010";
      if (compInstruction.equals("D-A"))
          return "0010011";
      if (compInstruction.equals("A-D"))
          return "0000111";
      if (compInstruction.equals("D&A"))
          return "0000000";
      if (compInstruction.equals("D|A"))
          return "0010101";
      if (compInstruction.equals("M"))
          return "1110000";
      if (compInstruction.equals("!M"))
          return "1110001";
      if (compInstruction.equals("-M"))
          return "1110011";
      if (compInstruction.equals("M+1"))
          return "1110111";
      if (compInstruction.equals("M-1"))
          return "1110010";
      if (compInstruction.equals("D+M"))
          return "1000010";
      if (compInstruction.equals("D-M"))
          return "1010011";
      if (compInstruction.equals("M-D"))
          return "1000111";
      if (compInstruction.equals("D&M"))
          return "1000000";
      if (compInstruction.equals("D|M"))
          return "1010101";
        
      return null;
  }

  public String jump(String jumpInstruction) {
      if (jumpInstruction == null) 
          return "000";
      if (jumpInstruction.equals("JGT"))
          return "001";
      if (jumpInstruction.equals("JEQ")) 
          return "010";
      if (jumpInstruction.equals("JGE"))
          return "011";
      if (jumpInstruction.equals("JLT"))
          return "100";
      if (jumpInstruction.equals("JNE"))
          return "101";
      if (jumpInstruction.equals("JLE"))
          return "110";
      if (jumpInstruction.equals("JMP"))
          return "111";

      return null;
  }
}
