import java.util.Random;
import java.util.Scanner;

public class kazuate {
    public static void main(String[] args) {
        // ランダムな数を生成して設定する
        Random random = new Random();
        int targetNumber = random.nextInt(90) + 10;

        // ユーザの入力を受け付ける
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int guessedNumber = 0;

        // 最大5回までの入力を処理する
        while (attempts < 5) {
            System.out.print("予想した数を入力してください（10から99までの間の数）: ");
            guessedNumber = scanner.nextInt();
            attempts++;

            // 入力された数と設定された数を比較する
            if (guessedNumber == targetNumber) {
                System.out.println("当たり！おめでとうございます！");
                break;
            } else {
                if (guessedNumber > targetNumber) {
                    System.out.println("入力した数は設定された数より大きいです。");
                    // 20以上差がある場合のメッセージを表示する
                    if (Math.abs(guessedNumber - targetNumber) >= 20) {
                        System.out.println("20以上差があります。");
                    }
                } else {
                    System.out.println("入力した数は設定された数より小さいです。");
                    // 20以上差がある場合のメッセージを表示する
                    if (Math.abs(guessedNumber - targetNumber) >= 20) {
                        System.out.println("20以上差があります。");
                    }
                }

            }
        }

        // 最大試行回数までに当たりが出なかった場合のメッセージを表示する
        if (attempts == 5 && guessedNumber != targetNumber) {
            System.out.println("残念！正解は " + targetNumber + " でした。");
        }

        // Scannerを閉じる
        scanner.close();
        
    }
}
