package example.nio;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        example0();
        example1();
    }

    private static void example1() {
        Observable<String> observable = Observable.fromIterable(List.of("Маша", "Даша", "Катя", "Женя"));
        observable.subscribe(name-> System.out.println("name = " + name));

        Observable.fromArray("Вася", "Гриша", "Костя")
                .filter(s->s.endsWith("я"))
                .doOnNext(s-> System.out.println("исходное имя = " + s))
                .map(String::toUpperCase)
                .doOnNext(s-> System.out.println("новое имя = " + s))
                .doAfterNext(s-> System.out.println("***"))
                .subscribe();

    }

    public static void example0(){
        Observable<String> observable = Observable.fromIterable(List.of("aaa", "bbb", "ccc", "ddd"));

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("подписались");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("s = " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("ошибка! "+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("завершение");
            }
        };
        observable.subscribe(observer);

    }
}