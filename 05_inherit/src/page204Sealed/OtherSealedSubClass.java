package page204Sealed;

public class OtherSealedSubClass {
    public static void main(String[] args) {

    }
}

// 由于SimpleValue没有permits字句，SimpleString和SimpleValue也不在同一个文件，以下class声明会报错
// java: 类不得扩展密封类：page204Sealed.SimpleValue（因为它未列在其 'permits' 子句中）
// class SimpleString extends SimpleValue {}

final class CrossFileNumber extends CrossFile {}
final class CrossFileString extends CrossFile {}
