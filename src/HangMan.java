import java.util.*;

public class HangMan {

    public static List<String> nouns = Arrays.asList("ambition", "village", "analyst", "river", "actor", "signature", "people", "perception", "variation", "hearing", "version", "injury", "distribution", "flight", "hospital", "phone", "painting", "chest", "studio", "media", "transportation", "sympathy", "opinion", "importance", "desk", "mixture", "currency", "wedding", "girlfriend", "replacement");
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        printHANGMAN();

        System.out.println("New game? Y/N");

        while (true) {

            String in = input.nextLine();
            if (in.toLowerCase().equals("y")){

                String word = getRandomStringFromArray(nouns);
                int AmountofMistakes = 0;
                String result = getLowerDash(word);
                List<String> mistakes = new ArrayList<>();

                while (AmountofMistakes <= 6) {

                    getHangMan(AmountofMistakes);
                    if (AmountofMistakes == 6) {
                        youLoseMethod();
                        break;
                    }

                    if (!result.contains("_")) {
                        youWinMethod();
                        break;
                    }

                    informationDash(AmountofMistakes, result, mistakes);
                    System.out.println("Write letter: ");
                    String letter = input.nextLine();

                    if (!mistakes.contains(letter) && !result.contains(letter)) {

                        if (Character.isLetter(letter.charAt(0))) {

                            if (letter.length() > 1) {
                                System.err.println("Only one letter!");
                                continue;
                            }

                            Brain brain = new Brain(word, letter, AmountofMistakes, result, mistakes);
                            brain = brain.Braining();

                            word = brain.wordB;
                            AmountofMistakes = brain.amountOfMistakesB;
                            result = brain.resultB;
                            mistakes = brain.mistakesB;

                        } else if (!Character.isLetter(letter.charAt(0))) {
                            System.err.println("Only letters!");
                            continue;
                        }

                    } else {
                        System.err.println("You have already tried letter: " + letter);
                        continue;
                    }

                }
            }else if (in.toLowerCase().equals("n")){
                printGAMEOVER();
                break;
            } else  {
                System.err.println("Invalid input!, Only Y/N");
                continue;
            }


        }




    }

    public static String getRandomStringFromArray(List<String> list) {
        Random rand = new Random();

        int randomNum = rand.nextInt(list.size());
        return list.get(randomNum);
    }

    public static void getHangMan(int AmountOfMistakes) {

        switch (AmountOfMistakes) {
            case 0:
                System.out.println("      _______\n" + "     |/      |\n" + "     |      \n" + "     |      \n" + "     |       \n" + "     |      \n" + "     |\n" + " ____|___");
                break;
            case 1:
                System.out.println("      _______\n" + "     |/      |\n" + "     |      (_)\n" + "     |      \n" + "     |       \n" + "     |     \n" + "     |\n" + " ____|___");
                break;
            case 2:
                System.out.println("      _______\n" + "     |/      |\n" + "     |      (_)\n" + "     |       |\n" + "     |       |\n" + "     |      \n" + "     |\n" + " ____|___");
                break;
            case 3:
                System.out.println("      _______\n" + "     |/      |\n" + "     |      (_)\n" + "     |      /|\n" + "     |       |\n" + "     |      \n" + "     |\n" + " ____|___");
                break;
            case 4:
                System.out.println("      _______\n" + "     |/      |\n" + "     |      (_)\n" + "     |      /|\\\n" + "     |       |\n" + "     |      \n" + "     |\n" + " ____|___");
                break;
            case 5:
                System.out.println("      _______\n" + "     |/      |\n" + "     |      (_)\n" + "     |      /|\\\n" + "     |       |\n" + "     |        \\\n" + "     |\n" + " ____|___");
                break;
            case 6:
                System.out.println("      _______\n" + "     |/      |\n" + "     |      (_)\n" + "     |      /|\\\n" + "     |       |\n" + "     |      / \\\n" + "     |\n" + " ____|___");
        }

    }

    public static String getLowerDash(String word) {
        int size = word.length();
        String dashs = "";

        for (int i = 0; i < size; i++) {
            dashs += "_";
        }
        return dashs;
    }

    public static void printHANGMAN() {
        System.out.println(" _                                             \n" + "| |                                            \n" + "| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \n" + "| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n" + "| | | | (_| | | | | (_| | | | | | | (_| | | | |\n" + "|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_|\n" + "                    __/ |                      \n" + "                   |___/                       \n");
    }

    public static void printGAMEOVER() {
        System.out.println("  _____                         ____                 \n" + " / ____|                       / __ \\                \n" + "| |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ \n" + "| | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|\n" + "| |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   \n" + " \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   ");
    }

    public static void youLoseMethod() {
        System.out.println("you lose(");
        System.out.println("Play again? Y/N");
    }

    public static void youWinMethod() {
        System.out.println("Congartulations, you win!!!");
        System.out.println("Play again? Y/N");
    }

    public static void informationDash(int AmountofMistakes, String result, List<String> mistakes) {
        System.out.println(result + "         " + "Amount of mistakes: " + AmountofMistakes + "         " + "Mistakes: " + mistakes);
    }

    public static class Brain {

        String wordB;
        String letterB;
        int amountOfMistakesB;
        String resultB;
        List<String> mistakesB;


        public Brain Braining() {
            if (wordB.contains(letterB)) {
                System.out.println("Right!");
                resultB = getLowerDash(wordB, letterB, resultB);
            } else if (!wordB.contains(letterB)) {
                mistakesB.add(letterB);
                amountOfMistakesB++;
            }

            return this;
        }

        public Brain(String word, String letter, int amountOfMistakes, String result, List<String> mistakes) {
            this.wordB = word;
            this.letterB = letter;
            this.amountOfMistakesB = amountOfMistakes;
            this.resultB = result;
            this.mistakesB = mistakes;
        }

        public static String getLowerDash(String word, String letter, String lowerdash) {
            StringBuilder stringLower = new StringBuilder(lowerdash);
            StringBuilder stringword = new StringBuilder(word);


            for (int j = 0; j < word.length(); j++) {

                if (stringword.toString().contains(letter)) {
                    int index = stringword.indexOf(letter);
                    stringLower.setCharAt(index, letter.charAt(0));
                    stringword.setCharAt(index, '0');

                }

            }

            return stringLower.toString();
        }


    }

}

