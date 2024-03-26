package other;

import Memento.Memento;

import java.time.LocalDateTime;

public class Main
{

    public static void main(String[] args) {

            Memento memento = new Memento("fortnite", "Get 10 kills before reaching 10 players remaining", 10, 3,LocalDateTime.of(2020, 2,23, 11, 54));

            JsonWriter writer = new JsonWriter();
            writer.writeMementoToJson(memento);

    }
}
