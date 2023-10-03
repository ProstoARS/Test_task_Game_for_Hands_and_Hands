package service;

import io.Input;
import io.Output;
import model.Gamer;
import repository.IGamerRepository;

import java.util.List;

public class CharacterSelection implements UserAction {

    private final Output out;

    public CharacterSelection(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Выбор персонажа";
    }

    @Override
    public int execute(Input input, IGamerRepository repository) {
        out.println("==== Выбор персонажа ====");
        List<Gamer> gamerList = repository.findAll();
        int index = 1;
        do {
            for (Gamer gamer : gamerList) {
                out.println(index + ".  -= " + gamer.getName() + " =-");
                out.println("  Здоровье:  " + gamer.getHealth());
                out.println("  Атака:     " + gamer.getAttack());
                out.println("  Защита:    " + gamer.getProtection());
                out.println("  Урон:      " + gamer.getDamage());
                index++;
            }
            int result = input.askInt("выберите персоножа: ");
            if (result <= 0 || result > gamerList.size()) {
                out.println("Вы ввели некорректные данные");
            } else {
                return result;
            }
        } while (true);
    }
}
