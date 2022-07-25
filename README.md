
:construction: In progress... / Em construção...

</br>

⚠️ English and Portuguese explanation / Explicações em inglês e português

</br>


# Code evolution before and after Java 8.

</br>

java 1.8 or + is needed.

</br>

</br>
</br>

Interface Funcional
Interface que ós tem 1 único método abstrato. Além desse método ela pode ter outros métodos, contanto que sejam default ou 'static'.
Lambda funciona somente com uma Inteface Funcional
Essa estrutura é fundamental, pois assim o compilador sabe exatamente que o corpo da expressão lambda que escrevemos é a implementação de seu único método abstrato

metodos default
metodos que tem implementação dentro de uma interface
assim as interfaces podem evoluir, adicionar novos metodos, sem quebrar as classes que as implementam
exemplo:
</br>
dentro da interface List foi inserido um novo método default:
```
default void sort(Comparator<? super E> c) {
	Object[] a = this.toArray();
	Arrays.sort(a, (Comparator) c);
	ListIterator<E> i = this.listIterator();
	for (Object e : a) {
		i.next();
		i.set((E) e);
	}
}
```

</br>
</br>

---------------------------------------------------------------------------------------------------------------

</br>
</br>

Explicação/evoluçao do código (foreach -> classe separada -> classe anônima -> lambdas -> referenced methods)

</br> 

A partir do Java 8 surgiu o método default foreach da interface Iterable (mãe de todas as listas)
```
default void forEach(Consumer<? super T> action) {
	Objects.requireNonNull(action);
	for (T t : this) {
		action.accept(t);
	}
}
```


</br>

O método default foreach recebe um Consumer

Vamos ver abaixo, nos exemplos, a evolução do código e como ele chegou nos referenced methods


</br>
</br>


* Exemplo A: Implementando o Consumer em uma classe

A maneira mais fácil de entender a evolução do código é começar pelo começo.
Primeiro criamos uma classe que implementa a interface Consumer
```
class PrintStringAsAnonimousClassConsumer implements Consumer<String>{
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
}
```

para ser chamada dentro do foreach
```
names.forEach(new PrintStringAsAnonimousClassConsumer());
```

porém não seria bom ter uma classe só para isso.
para que você não precise criar uma classe separada para ser usada apenas uma vez, existem as classes anônimas.

</br>
</br>


* Exemplo B: Usando classe anônima sendo referenciada por uma var

Em vez de você ter que criar uma classe somente para ser usada uma vez,
voce pode cria-la dando um new diretamente na Interface
você só pode dar um new na interface se você for fornecer os métodos de implementação ali mesmo
```
Consumer<String> consumer = new Consumer<String>() {
	@Override
	public void accept(String s) {
		System.out.println("With anonymous class referenced by a var: " + s);
	}
};
names.forEach(consumer);
```

</br>
</br>


* Exemplo C: Usando classe anônima diretamente no foreach

Podemos passar o Consumer diretamente no foreach:
```
names.forEach(new Consumer<String>() {
	@Override
	public void accept(String s) {
		System.out.println("With anonymous class directly in foreach: " + s);
	}
});
```

</br>
</br>


* Exemplo D: Lambda

O compilador já sabe que o foreach recebe um Consumer,
então você só precisa passar o parametro, uma flecha e o corpo do método
```
names.forEach((String s) -> {
	System.out.println("With anonymous class directly in foreach: " + s);
});
```

</br>
</br>


* Exemplo E: Lambda

No exemplo acima, podia ter mais de um parâmetro e mais de uma linha dentro do método, executando mais códigos
como, no caso acima, só tem um parametro e dentro das chaves apenas um comando, podemos simplificar ainda mais para:
```
names.forEach(s -> System.out.println("With anonymous class directly in foreach: " + s));
```

</br>
</br>


* Exemplo F: Lambda referenciado por uma var, fora do foreach

Os lambdas funcionam sempre que você tem uma Interface Funcional (interface com apenas 1 método abstrato)
ela pode ir diretamente no foreach (melhor)
ou ser referenciada por uma var (apenas exemplo didático, não é muito usada assim) como no exemplo abaixo:
```
Consumer<String> consumerWithLambda = s1 -> System.out.println("Another example of lambda: " +s1);
names.forEach(consumerWithLambda);
