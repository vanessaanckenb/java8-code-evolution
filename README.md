</br>

⚠️ English and Portuguese explanation / Explicações em inglês e português

</br>


# Code evolution before and after Java 8.

</br>

java 1.8 or + is needed.

</br>
</br>

**Funcional Interface**

A Interface that has only one abstract method.

It can have other methods if its default or static.

Lambda works only with Funcional Interfaces. This structure is essencial because this way compiler knows exaclty that the body of the method we write is the implementation of the only abstract method of the Interface.

</br>
</br>

**Metodos Default**

Methods with implementation in an Interface

After this all Interfaces are able to evolve, having new methods without break classes that implements it.

Example:
</br>
We have a new method default in List Interface.
If it wasnt default without a body, all classes that implements List interface would need to implement this method as well and they would break.
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
</br>

Explanation/evolution of the code (foreach -> separated classes -> anonymous classes -> lambdas -> referenced methods)

</br> 

From Java 8 we got foreach default method of Iterable Interface 
```
default void forEach(Consumer<? super T> action) {
	Objects.requireNonNull(action);
	for (T t : this) {
		action.accept(t);
	}
}
```


</br>

foreach receives a Consumer interface

We're going to see bellow java code evolution and how it became a referenced method.


</br>
</br>


* **Example A: Implementing Consumer in a Class**

The easier way to understand java code evolution is to start from begining.

First of all lets create a class that implements Consumer:
```
class PrintStringAsAnonimousClassConsumer implements Consumer<String>{
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
}
```

now we're able to call it in our foreach:
```
names.forEach(new PrintStringAsAnonimousClassConsumer());
```

But in this case (small method, only one command), its not a better way to do it.

to have one class only to be used one time with a method with only one command.

Anonymous class exists so you dont need to create a separted class.

</br>
</br>


* **Example B: Using anonymous class referenced by a var**

You dont need to create a class to use it only once, you can do it directly in you code.

you are able to instanciate an interface (new) only if you implement its method as bellow:

```
Consumer<String> consumer = new Consumer<String>() {
	@Override
	public void accept(String s) {
		System.out.println("With anonymous class referenced by a var: " + s);
	}
};
names.forEach(consumer);
```

Compiler creates a class that implements this Interface

Warning! This generated class by compiler only exists when the class is runned

It can be more difficult to debug when we have an exception

Its a very good way to use anonymous class when its simple and not too big, other way its better to create a separeted class implementing the interface


</br>
</br>


* **Example C: Using anonymous class directly in foreach**
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


* **Example D: Lambda**

lambda = funtional interface = interface with only one abstract method

complier knows this foreach receives a Consumer and it das only one method to be implemented

so you just need to give it the parameters -> body method / method implementation

```
names.forEach((String s) -> {
	System.out.println("With anonymous class directly in foreach: " + s);
});
```

</br>
</br>


* **Example E: Lambda**

In the example above, we could have more than one parameter, more the one command in the method

but this method has only one parameter and one instruction, it can be simplified like this:
```
names.forEach(s -> System.out.println("With anonymous class directly in foreach: " + s));
```
names is a list of string
every item is a String
so Consumer<T> will be Consumer<String>
s represents every list item in foreach
System.out.println is the body of the method, getting the parameter s
</br>
</br>


* **Example F: Lambda in a var**

this example is only another way to understand, its not used:
```
Consumer<String> consumerWithLambda = s1 -> System.out.println("Another example of lambda: " +s1);
names.forEach(consumerWithLambda);
```

</br>
</br>

* **Example G: method referenced**

Its a type of simples lambdas

better way to read code.
```
names.forEach(System.out::println);
System.out.println();
```

</br>
</br>

* **Example H: method referenced in a var**

this example is only another way to understand, its not used:
```
Consumer<String> consumerWithMethodReferenced = System.out::println;
names.forEach(consumerWithMethodReferenced);
```


</br>
</br>

--------------------------------------------------------------------------------------

</br>
</br>

* **Interface Funcional**

Interface que só tem 1 único método abstrato. Além desse método ela pode ter outros métodos, contanto que sejam default ou 'static'.

Lambda funciona somente com uma Inteface Funcional

Essa estrutura é fundamental, pois assim o compilador sabe exatamente que o corpo da expressão lambda que escrevemos é a implementação de seu único método abstrato

</br>
</br>

* **Metodos Default**

metodos que tem implementação dentro de uma interface

assim as interfaces podem evoluir, adicionar novos metodos, sem quebrar as classes que as implementam

Exemplo:
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


* **Exemplo A: Implementando o Consumer em uma classe**

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


* **Exemplo B: Usando classe anônima sendo referenciada por uma var**

Em vez de você ter que criar uma classe somente para ser usada uma vez,

voce pode cria-la dando um new diretamente na Interface

você só pode dar um new na interface se você for fornecer os métodos de implementação ali mesmo

Nesse caso o compilador gera uma classe anônima que implementa a interface

Cuidado com as classes anônimas pois podem dificultar a leitura

Como o compilador realmente gera uma classe, essa classe só existe quando é executada. 

Isso pode ser mais difícil de se entender quando recebemos uma exceção ou depuramos o código pois a classe não está presente no nosso código fonte.

Dica: quando o comando é simples e curto deve usar as classes anônimas, quando tem muito código melhor separar a classe e implementar a interface

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


* **Exemplo C: Usando classe anônima diretamente no foreach**

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


* **Exemplo D: Lambda**

Lambda = interface funcional = interface com um único método abstrato

O compilador já sabe que o foreach recebe um Consumer

então você só precisa passar o parametro, uma flecha e o corpo do método

```
names.forEach((String s) -> {
	System.out.println("With anonymous class directly in foreach: " + s);
});
```

</br>
</br>


* **Exemplo E: Lambda**

No exemplo acima, podia ter mais de um parâmetro e mais de uma linha dentro do método, executando mais códigos

como, no caso acima, só tem um parametro e dentro das chaves apenas um comando, podemos simplificar ainda mais para:
```
names.forEach(s -> System.out.println("With anonymous class directly in foreach: " + s));
```
Como é uma lista de String, cada item é uma String
Então o Consumer<T> será Consumer<String> (por debaixo dos panos)
o s representa cada item da lista passado no foreach
o System.out.println é o unico comando dado dentro do método, que pega o s como parametro.
</br>
</br>


* **Exemplo F: Lambda referenciado por uma var, fora do foreach**

Os lambdas funcionam sempre que você tem uma Interface Funcional (interface com apenas 1 método abstrato)

ela pode ir diretamente no foreach (melhor)

ou ser referenciada por uma var (apenas exemplo didático, não é muito usada assim) como no exemplo abaixo:
```
Consumer<String> consumerWithLambda = s1 -> System.out.println("Another example of lambda: " +s1);
names.forEach(consumerWithLambda);
```

</br>
</br>

* **Exemplo G: method referenced**

É um tipo especial de Lambda

usado para criar Lambdas simples referenciando métodos

vantagem de uso: legibilidade
```
names.forEach(System.out::println);
System.out.println();
```

</br>
</br>

* **Exemplo G: method referenced in a var**

Apenas para fins didaticos:
```
Consumer<String> consumerWithMethodReferenced = System.out::println;
names.forEach(consumerWithMethodReferenced);
```
