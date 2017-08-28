
import java.io.BufferedReader;
import java.lang.reflect.Array;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;


   /*

     Исходные данные: текстовый файл со средней длиной строки равной 10 символам (файл или прошить текст в коде).
    В реализациях используйте наиболее подходящие имплементации коллекций!

    Задание 1: Подсчитайте количество различных слов в файле.
    Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту).
    Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
    Задание 4: Выведите на экран все строки файла в обратном порядке.
    Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
    Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.

     */

public class Task3 {


    private static Iterator<String> getIterator(final ArrayList<String> array) {
        return new Iterator<String>() {
            private int count = array.size();
            private int index = array.size()-1;
            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public String next() {
                if (index >= 0) {
                    return array.get(index--);
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }

            //для теста пусть будет тоже самое, что и next()
            public String prevEl() {
                if (index < count) {
                    return array.get(index++);
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove item from array.");
            }
        };
    }







    public static void main(String[] args) {


                try{
                    FileInputStream fstream = new FileInputStream("C:\\Users\\TurUli\\Desktop\\zdf\\Task2808.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    StringTokenizer sTok;
                    ArrayList<String> arrWords= new ArrayList();
                    ArrayList<String> arrStrings= new ArrayList();
                    java.lang.Object[] str  =  br.lines().toArray();
                    for (int i = 0; i< str.length; i++)
                    {
                        arrStrings.add((String) str[i]);
                        sTok = new StringTokenizer(str[i].toString(), " ");
                        while (sTok.hasMoreTokens())
                        {
                            String strToken = sTok.nextToken();
                            arrWords.add(strToken);
                        }
                    }


                    // Задание 1: Подсчитайте количество различных слов в файле.
                    ArrayList<String> arrWordsUnique = new ArrayList<String>(new HashSet<String>(arrWords));
                    System.out.println("Задание 1, различных слов в файле: " + arrWordsUnique.size());
                    System.out.println();

                   // Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту).
                    System.out.println("Задание 2, отсортированный список слов файла: ");

                    Collections.sort(arrWordsUnique, new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            int result =  o1.length() - o2.length();
                            if (result !=0) return (int) (result/Math.abs(result));
                            result = o1.compareTo(o2);
                            if (result !=0) return (int) (result/Math.abs(result));
                            return (result != 0) ? (int)(result/Math.abs(result)) : 0;

                        }
                    });

                  for (int i=0; i< arrWordsUnique.size(); i++)
                      System.out.println(arrWordsUnique.get(i) + " ");
                   System.out.println();


                  //  Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.
                    System.out.println("Задание 3, частота использования слов в файле: ");
                    for (int i = 0; i< arrWordsUnique.size(); i++)
                        System.out.print("(" + arrWordsUnique.get(i) + ", " + Collections.frequency(arrWords,arrWordsUnique.get(i)) + ")");
                    System.out.println();

                    //  Задание 4: Выведите на экран все строки файла в обратном порядке.

                    System.out.println("Задание 4, строки в обратном порядке ");
                    Collections.reverse(arrStrings);
                    for (int i=0; i< arrStrings.size(); i++)
                        System.out.println(arrStrings.get(i));
                    System.out.println();

                    //  Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.
                    System.out.println("Задание 5, обход в обратном порядке итератором ");
                    Iterator<String> integerIterator = Task3.getIterator(arrStrings);
                    while (integerIterator.hasNext())
                        System.out.println(integerIterator.next());
                    System.out.println();
                    // Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.
                    System.out.println("Задание 6, вывод строк заданных номеров.  ");
                    System.out.println("Введите номера строк для вывода на экран, от " + "1" + " до " + arrStrings.size() + ": ");
                    Scanner scanner = new Scanner(System.in);
                    ArrayList<Integer> arrIndex = new ArrayList<>();
                    String s = scanner.nextLine();
                    sTok = new StringTokenizer(s, " ");
                    while (sTok.hasMoreTokens())
                    {   int n = Integer.parseInt(sTok.nextToken());
                        arrIndex.add(n);
                    }


                    for (int i = 1; i<= arrStrings.size(); i++)
                        if (arrIndex.contains(i))
                            System.out.println(arrStrings.get(i-1));


       }
                catch (IOException e){
                    System.out.println("Ошибка");
                }





    }


}
