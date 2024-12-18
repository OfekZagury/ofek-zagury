import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    private BufferedReader bReader;
    private String currentLine;
    private String nextLine;
    public enum InstructionType {
        A_INSTRUCTION, C_INSTRUCTION, L_INSTRUCTION
    }

    public Parser(File hackFile) throws IOException {
        this.bReader = new BufferedReader(new FileReader(hackFile));
        this.currentLine = null;
    }

    public boolean hasMoreLines() throws IOException {
        this.bReader.mark(10000000);
        while ((this.nextLine = this.bReader.readLine()) != null) {
            String trimLine = this.nextLine.trim();
            if (!trimLine.startsWith("/") && !trimLine.isEmpty()) {
                this.bReader.reset();
                return true;
            }
        }
        this.bReader.reset();
        return false;
    }

    public void advance() throws IOException {
        String trimLine;
        while (true) {
            this.currentLine = this.bReader.readLine();
            if (this.currentLine == null) {
                break; 
            }
            trimLine = this.currentLine.trim();
            if (!trimLine.startsWith("/") && !trimLine.isEmpty()) {
                break;
            }
}
}

    public InstructionType instructionType() {
        String trimLine = this.currentLine.trim();
        if (trimLine.startsWith("@")) {
            return InstructionType.A_INSTRUCTION;
        } 
        if (trimLine.startsWith("(") && this.currentLine.endsWith(")")) {
            return InstructionType.L_INSTRUCTION;
        }
        return InstructionType.C_INSTRUCTION;
    }

    public String symbol() {
        String trimLine = this.currentLine.trim();
        if (trimLine.startsWith("@")) {
            return trimLine.substring(1);
        }
        if (trimLine.startsWith("(")) {
            return trimLine.substring(1, (trimLine.length() - 1));
        }
        return null;
    }

    public String dest() {
        String trimLine = currentLine.trim();
        int indexOfEquals = trimLine.indexOf('=');
        if (indexOfEquals == -1) {
            return null; 
        }
        return trimLine.substring(0, indexOfEquals);
    }

    public String comp() {
        String trimLine = currentLine.trim();
        int indexOfEquals = trimLine.indexOf('=');
        int indexOfSemiColon = trimLine.indexOf(';');
        if (indexOfEquals != -1) {
            if (indexOfSemiColon != -1) {
                return trimLine.substring(indexOfEquals + 1, indexOfSemiColon); 
            } else {
                return trimLine.substring(indexOfEquals + 1); 
            }
        } else {
            if (indexOfSemiColon != -1) {
                return trimLine.substring(0, indexOfSemiColon); 
            }
        }
        return trimLine; 
    }

    public String jump() {
        String trimLine = currentLine.trim();
        int indexOfSemiColon = trimLine.indexOf(';');
        if (indexOfSemiColon != -1) {
            return trimLine.substring(indexOfSemiColon + 1); 
        }
        return null; 
    }
}



    

