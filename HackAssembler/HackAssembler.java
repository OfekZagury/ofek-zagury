import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class HackAssembler {
    public static void main(String[] args) {
    File hackFile = new File(args[0]); 
    String outPutFileName = args[0].substring(0, args[0].indexOf('.'));
    outPutFileName += ".hack";
    File outputFile = new File(outPutFileName);
    try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(outputFile))) {
    SymbolTable symbolTable = new SymbolTable(); // Creating empty Symbol Table
    symbolTable.addEntry("R0", 0); // initielizing pre-defined symbols
    symbolTable.addEntry("R1", 1);
    symbolTable.addEntry("R2", 2);
    symbolTable.addEntry("R3", 3);
    symbolTable.addEntry("R4", 4);
    symbolTable.addEntry("R5", 5);
    symbolTable.addEntry("R6", 6);
    symbolTable.addEntry("R7", 7);
    symbolTable.addEntry("R8", 8);
    symbolTable.addEntry("R9", 9);
    symbolTable.addEntry("R10", 10);
    symbolTable.addEntry("R11", 11);
    symbolTable.addEntry("R12", 12);
    symbolTable.addEntry("R13", 13);
    symbolTable.addEntry("R14", 14);
    symbolTable.addEntry("R15", 15);
    symbolTable.addEntry("SCREEN", 16384);
    symbolTable.addEntry("KBD", 24576);
    symbolTable.addEntry("SP", 0);
    symbolTable.addEntry("LCL", 1);
    symbolTable.addEntry("ARG", 2);
    symbolTable.addEntry("THIS", 3);
    symbolTable.addEntry("THAT", 4);
     
    // First Pass
    Parser parserFirstPass = new Parser(hackFile);
    int lineIndex = 0;
    while (parserFirstPass.hasMoreLines()) {
        parserFirstPass.advance();
        if (parserFirstPass.instructionType() == Parser.InstructionType.L_INSTRUCTION) {  
            symbolTable.addEntry(parserFirstPass.symbol(), lineIndex);
        } else {
            lineIndex++;
        }
    }

    // Second Pass
Parser parserSecondPass = new Parser(hackFile);
Code code = new Code();
int symbolTableIndex = 16;
while (parserSecondPass.hasMoreLines()) {
    parserSecondPass.advance();
    
    if (parserSecondPass.instructionType() == Parser.InstructionType.A_INSTRUCTION) {
        String symbol = parserSecondPass.symbol();
        
        try {
            int address = Integer.parseInt(symbol); 
            bWriter.write(String.format("%16s", Integer.toBinaryString(address)).replace(' ', '0')); 
            bWriter.newLine();
        } catch (NumberFormatException e) {
            if (!(symbolTable.contains(symbol))) {
                symbolTable.addEntry(symbol, symbolTableIndex);
                symbolTableIndex++;
            }
            bWriter.write(String.format("%16s", Integer.toBinaryString(symbolTable.getAddress(parserSecondPass.symbol()))).replace(' ', '0'));
            bWriter.newLine();
        }
    }
    if (parserSecondPass.instructionType() == Parser.InstructionType.C_INSTRUCTION) {
        bWriter.write("111" + code.comp(parserSecondPass.comp()) + code.dest(parserSecondPass.dest()) + code.jump(parserSecondPass.jump()));
        bWriter.newLine();
    }
}
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
