class Main {
    public static void main(String[] args) throws InterruptedException {

        EntityRace chicken = new EntityRace("Курица");
        EntityRace egg = new EntityRace("Яйцо");

        // Запускаем оба потока
        chicken.start();
        egg.start();

        // Определяем, какой поток завершится первым
        String winner = getFirstToFinish(chicken, egg);
        System.out.println("Первым на свет появилось: " + winner);
    }

    // Метод для определения, какой поток завершится первым
    public static String getFirstToFinish(Thread entity1, Thread entity2) throws InterruptedException {
        while (true) {
            if (!entity1.isAlive()) {
                entity2.join();  // Ждем завершения второго потока
                return entity1.getName();  // Возвращаем имя завершившегося потока
            }
            if (!entity2.isAlive()) {
                entity1.join();  // Ждем завершения первого потока
                return entity2.getName();  // Возвращаем имя завершившегося потока
            }
        }
    }
}

// Класс для представления участников гонки: курицы и яйца
class EntityRace extends Thread {
    public EntityRace(String entityName) {
        super(entityName);  // Передаем имя в конструктор родительского класса Thread
    }

    // выводим имя текущего участника
    public void run() {
        System.out.println(getName() + " участвует в гонке!");
    }
}
