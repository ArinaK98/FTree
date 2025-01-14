package FT_Project.view;

import java.time.LocalDate;
import java.util.Scanner;

import FT_Project.presenter.Presenter;

public class Console implements View{
    private static final String INPUT_ERROR = "Вы ввели неверное значение";
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public Console(){
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        menu = new MainMenu(this);
    }

    @Override
    public void printAnswer(String text){
        System.out.println(text);
    }

    @Override
    public void start(){
        hello();
        while (work){
            printMenu();
            execute();
        }
    }
    public void finish(){
        System.out.println("Завершение работы");
        work = false;
    }
    public void sortByAge(){
        presenter.sortByAge();
    }

    public void sortByName(){
        presenter.sortByName();
    }

    public void sortById(){
        presenter.sortById();
    }
    public void remove() {
        System.out.println("Введите Id удаляемой записи");
        String is = scanner.nextLine();
        long id = Integer.parseInt(is);
        presenter.remove(id);
    }

    public void getHumansListInfo() {
        presenter.getHumansListInfo();
    }

    public void addHuman() {
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите пол (Male/Female)");
        String gender = scanner.nextLine();
        System.out.println("Введите дату рождения в формате (yyyy-mm-dd)");
        String dob = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(dob);
        presenter.addHuman(name, gender, birthDate);
    }

    public void addChildToParents(){
        System.out.println("Введите Id отца");
        String is1 = scanner.nextLine();
        long id1 = Integer.parseInt(is1);
        System.out.println("Введите Id матери");
        String is2 = scanner.nextLine();
        long id2 = Integer.parseInt(is2);
        System.out.println("Введите Id ребенка");
        String is3 = scanner.nextLine();
        long id3 = Integer.parseInt(is3);
        presenter.addChildToParents(id1, id2, id3);
    }
    
    
    public void load() {
        presenter.load();
    }
    public void save() {
        presenter.save();
    }
    private void hello(){
        System.out.println("Добро пожаловать");
    }
    
    private void execute(){
        String line = scanner.nextLine();
        if (checkTextForInt(line)){
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)){
                menu.execute(numCommand);
            }
        }
    }
    private boolean checkTextForInt(String text){
        if (text.matches("[0-9]+")){
            return true;
        } else {
            inputError();
            return false;
        }
    }
    private boolean checkCommand(int numCommand){
        if (numCommand <= menu.getSize()){
            return true;
        } else {
            inputError();
            return false;
        }
    }
    private void printMenu(){
        System.out.println(menu.menu());
    }
    private void inputError(){
        System.out.println(INPUT_ERROR);
    }
}


