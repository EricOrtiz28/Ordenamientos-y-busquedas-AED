/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni.aed.bignumbers;

import java.math.BigInteger;
import java.util.Iterator;

/**
 *
 * @author AndreP
 */
public class BigInt {
    private static final char LESS = '-';
    
    private Node head;
    private byte sign;  // 1: Positive; -1: Negative
           
    
    // CONSTRUCTORS
    public BigInt(){
        this.head = new Node();
        this.sign = 1;
    }   
    
    public BigInt(long value){
        this("" + value);
    }   
    
    public BigInt(String value){
    /*
        Consideramos que la cadena de entrada tiene uno o más dígitos opcionales,
        precedidos por el signo negativo
        FORMATO:
        [<signo menos>] <digito>+
        [ ] : Componente opcional
        + : Una o más repeticciones
        
        Nota: la cadena "0000345" es válida, pero el proceso de conversión
        ignorará los ceros precedentes. 
        Más detalles en la función extractPrecedentZeros
        */
        
        // Recortamos los espacios en blanco
        value.trim();
        
        //PREGUNTA 13
        // Verificamos si el string está vacío
        if (value.isEmpty()) {
            throw new IllegalArgumentException("La cadena de entrada no puede estar vacía.");
        }
        
        // 1. Determinamos el signo, por defecto positivo (1)
        sign = +1;
        
        if (value.charAt(0) == LESS){
            sign = -1;
            value = value.substring(1); // Remueve el '-'
        }
        
        // Verificamos si consiste solo de dígitos (usando los "Regular Expressions" de Java
        if (!value.matches("\\d+")) {
            throw new IllegalArgumentException("El formato de la cadena de entrada es inválido. Valor: " + value);
        }
        
        // 2. Extraemos los ceros precedentes
        value = extractPrecedentZeros(value);
        
        /*
        El número cero puede tener varias formas en la entrada de string 
        (Ej: "00000", "-000", "0")
        Internamente se representará como positivo, +0.
        `extractPrecedentZeros` ya returna la string nula, pero todavía no hemos
        anulado el negativo:
        */
        if(value.equals("0")){
            sign = +1;
        }
        
        // 3. Creamos la lista enlazada de grupos de tres dígitos
        head = new Node(); // Se crea un nodo temporal
        Node tail = head; 
        String digits;
        while(!value.equals("")){
            // Se calcula la localización del primer índice del substring
            // que consta de 3 dígitos o menos.
            int loc = Math.max(value.length() - Node.MAX_DIGITS, 0);
            
            digits = value.substring(loc); 
                                // Se extraen como máximo MAX_DIGITS dígitos (3)
                                // Si hay <3 dígitos, se corta todo

            // Actualizamos el número (sin el substring extraído previamente)
            value = value.substring(0,loc);
                                // En el caso de loc=0 se tiene una string ""
            
            
            Node block = new Node(digits);
            
            tail.next= block;
            tail = block;
        }
        head = head.next; // Remueve el nodo head ficticio inicial
            
    }   
    
    public BigInt (Node head){
        this.head = head;
        this.sign =  +1;
    }
   
    // ARITHMETIC OPERATIONS
    public BigInt sum(BigInt num){
        /* Se busca resolver los siguientes casos
         * 1: A +  B  -->  A + B
         * 2: A + -B  -->  A - B
         * 3:-A +  B  --> -(A - B)
         * 4:-A + -B  --> -(A + B)
         * Se concluye que se necesitan dos operaciones: 
         * suma positiva y resta básica (A > B)
         */
        
        // Caso 1: Suma positiva
        if (num.isPositive() && this.isPositive())
            return this.sumPos(num);

        // Caso 4: Se hace una suma positiva y se intercambia el signo, a negativo
        if (num.isNegative() && this.isNegative()) {
            BigInt result = this.sumPos(num);
            result.flipSign();
            return result;
        }
        // Caso 2 y 3: 
        //Primer paso, necesitamos conocer el mayor
        int newSign = this.compareTo(num);
                        // Si A>B, se mantiene el signo (Caso 2)
                        // Si A<B, el signo cambia después de la resta (caso 3)
        
        BigInt resultSubstraction;
        if (this.isPositive() & num.isNegative()){  //A es + y B -
            num.flipSign();
            resultSubstraction = this.subPos(num);
            num.flipSign();
        }
        else{  //A es - y B +
            this.flipSign();
            resultSubstraction = this.subPos(num);
            this.flipSign();
            if(newSign < 0)
                resultSubstraction.flipSign();
        }
        return resultSubstraction;
        
    }
    public boolean isPositive(){
        return this.sign > 0;
    }
    public boolean isNegative(){
        return this.sign < 0;
    }
    public void flipSign(){
        if (this.sign > 0)
            this.sign = -1;
        else this.sign= 1;
    }
    public BigInt sumPos(BigInt num){
        Node p, q, r, t;
        p = this.head;
        q = num.head;
        
        t = new Node(); //TEMP head
        r = t;
        short carry = 0;
        while(p != null && q != null){
            short sum = (short) (carry + p.value + q.value);
            
            r.next = new Node();
            r = r.next;
            
            r.value = (short) (sum % Node.MAX_VALUE);   //Calculo de la suma
            carry = (short) (sum / Node.MAX_VALUE);     //Calculo del acarreo
            
            // Siguientes 2 bloques a sumar
            p = p.next;
            q = q.next;
        }
        
        p = (p == null) ? q: p;  // Restablece p para apuntar 
                                 //a los bloques restantes
        
        while(p != null){
            r.next = new Node();
            r = r.next;
            
            r.value = (short) ((p.value + carry) % Node.MAX_VALUE);
            carry = (short) ((p.value + carry) / Node.MAX_VALUE);
            p = p.next;
        }
                       
        if(carry > 0){  //Desbordamiento
            r.next = new Node((short) carry);
        }
        return new BigInt(t.next);  //Quita el nodo cabeza ficticio
    }
    public BigInt sub(BigInt num){
        num.flipSign();
        BigInt result = this.sum(num);
        num.flipSign();
        return result;
    }
    private BigInt subPos(BigInt num) {
        Node p, q, r, t;
        boolean esNegativo = false;

        // Siempre resta el más pequeño del mayor
        // Si num es mayor, entonces el resultado es negativo
        if (this.compareTo(num) >= 0) { // this - num
            p = this.head;
            q = num.head;
        } else { // -(num - this)
            p = num.head;
            q = this.head;
            esNegativo = true; 
       }

        t = new Node(); // Nodo cabeza ficticio
        r = t;

        short prestamo = 0, minuendo; // para L es un minuendo

        while (p != null && q != null) {
            r.next = new Node();
            r = r.next;
            
            minuendo = (short) (p.value - prestamo);
            
            if (minuendo < q.value) {
                r.value = (short) (Node.MAX_VALUE + minuendo - q.value);
                prestamo = 1;
            } else {
                r.value = (short) (minuendo - q.value);
                prestamo = 0;
            }
            
            p = p.next;
            q = q.next;
        }

        // restablece p para apuntar a los bloques restantes
        p = (p == null) ? q : p;
        
        while (p != null) {
            r.next = new Node();
            r = r.next;
            
            r.value = (short) (p.value - prestamo);
            if (r.value < 0) {
                r.value += Node.MAX_VALUE;
                prestamo = 1;
            } else {
                prestamo = 0;
            }
            
            p = p.next;
        }

        BigInt result = new BigInt(t.next); 
        result = result.extractPrecedentZeros(); 
        if (esNegativo) result.flipSign(); 
        
        return result;
    }
    public BigInt simpleMult(BigInt R) {        // EJERCICIO 17
        BigInt L = this;  // Referencia al objeto actual
        BigInt product = new BigInt("0");  // Inicialmente, el producto es 0
        BigInt term = new BigInt(L.toString());  // Una copia del valor absoluto de L
        
        // Si alguno de los valores es cero, retorna cero
        if(L.isZero() || R.isZero())
            return product;
        
        // Asegurarse de que el término (term) es positivo
        if (term.isNegative()) {
            term.flipSign();
        }

        BigInt limit = new BigInt(R.toString());  // Una copia del valor absoluto de R

        // Asegurarse de que el límite (limit) es positivo
        if (limit.isNegative()) {
            limit.flipSign();
        }

        // Usar el método `compareTo` para la condición del bucle
        BigInt counter = new BigInt("0");
        while (counter.compareTo(limit) < 0) {  // Mientras que el contador es menor que el límite
            product = product.sum(term);  // Suma el término al producto acumulativo
            counter.incr();  // Incrementa el contador
        }

        // Establecer el signo del producto
        if (L.isNegative() == R.isNegative()) {
            // Si L y R tienen el mismo signo, el producto es positivo
            if (product.isNegative()) {
                product.flipSign();
            }
        } else {
            // Si L y R tienen signos diferentes, el producto es negativo
            if (product.isPositive()) {
                product.flipSign();
            }
        }
        return product;
    }

    public BigInt mult(BigInt num) {        // EJERCICIO 18
        BigInt result = new BigInt();
        
        // Si alguno de los valores es cero, retorna cero
        if(this.isZero() || num.isZero())
            return new BigInt();
        
        BigInt multiplicand = new BigInt(this.toString()); // Copia del primer número
        BigInt multiplier = new BigInt(num.toString()); // Copia del segundo número

        // Inicializar para operaciones intermedias
        BigInt temp;
        int shift = 0;

        Node p = multiplier.head;
        while (p != null) {
            // Multiplicar multiplicand por el dígito actual p.value de multiplier
            temp = singleDigitMult(multiplicand, p.value);
            // Desplazar el resultado intermedio adecuadamente
            for (int i = 0; i < shift; i++) {
                temp = temp.multiplyByBase();
            }
            result = result.sum(temp);
            shift++;
            p = p.next;
        }

        // Ajustar el signo según los operandos originales
        if (this.sign != num.sign) {
            result.flipSign();
        }

        return result;
    }

    // Multiplica un BigInt por un solo dígito
    private BigInt singleDigitMult(BigInt bigInt, short digit) {
        BigInt result = new BigInt();
        Node p = bigInt.head;
        Node tempHead = new Node();
        Node r = tempHead;
        short carry = 0;

        while (p != null) {
            long prod = (long) p.value * digit + carry;
            short resValue = (short) (prod % Node.MAX_VALUE);
            carry = (short) (prod / Node.MAX_VALUE);

            r.next = new Node(resValue);
            r = r.next;
            p = p.next;
        }

        // Si queda algún acarreo al final
        if (carry > 0) {
            r.next = new Node(carry);
        }

        result.head = tempHead.next; // Omitir nodo cabeza ficticio
        return result;
    }

    // Multiplica un BigInt por el valor base (para el desplazamiento en la multiplicación)
    private BigInt multiplyByBase() {
        Node newNode = new Node(); // Nodo para el nuevo valor base
        newNode.next = this.head;
        this.head = newNode;
        return this;
    }
    
    public boolean isZero(){
        Node p = this.head;
        while(p.next != null){
            if(p.value == 0)
                p = p.next;
            else return false;
        }
        if(p.value != 0)
            return false;
        return true;
    }
    public BigInt simpleDiv(BigInt R) {     // EJERCICIO 19 PARTE 1
        // Primero, verificamos si R es 0 para evitar la división entre cero
        if (R.isZero()) {
            throw new ArithmeticException("División entre cero no está permitida.");
        }

        BigInt L = this;  // Referencia al objeto actual
        BigInt cociente = new BigInt("0");
        BigInt residuo = new BigInt(L.toString());  // Una copia del valor absoluto de L
        BigInt divisor = new BigInt(R.toString());  // Una copia del valor absoluto de R
        
        
        // Asegurarse de que residuo y divisor son positivos
        if (residuo.isNegative()) {
            residuo.flipSign();
        }
        if (divisor.isNegative()) {
            divisor.flipSign();
        }

        // Bucle para calcular el cociente y el residuo
        while (residuo.compareTo(divisor) >= 0) {
            residuo = residuo.sub(divisor);  
            cociente.incr();
        }

        // Establecer el signo del cociente
        if (L.isNegative() == R.isNegative()) {
            // Si L y R tienen el mismo signo, el cociente es positivo
            if (cociente.isNegative()) {
                cociente.flipSign();
            }
        } else {
            // Si L y R tienen signos diferentes, el cociente es negativo
            if (!cociente.isNegative()) {
                cociente.flipSign();
            }
        }
        if (cociente.isZero())
            cociente.sign = +1;

        return cociente;
    }
    
    public void reverseLinks() {
        Node current = head;
        Node prev = null;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public BigInt div(BigInt R) { // EJERCICIO 20: Método de división usando división larga
        // Verificar si R es 0 para evitar división entre cero
        if (R.isZero()) {
            throw new ArithmeticException("División entre cero no está permitida.");
        }

        // Definir y preparar las variables principales
        BigInt dividendoActual = new BigInt(this.toString()); // El dividendo que se está considerando en cada paso
        BigInt divisor = new BigInt(R.toString()); // El divisor con el que estamos dividiendo
        BigInt cociente = new BigInt("0"); // El cociente que estamos construyendo

        // Asegurarse de que ambos, dividendo y divisor, sean positivos para la división
        if (dividendoActual.isNegative()) {
            dividendoActual.flipSign();
        }
        if (divisor.isNegative()) {
            divisor.flipSign();
        }

        // Invertir la dirección de las ligas para el dividendo y el divisor
        dividendoActual.reverseLinks();
        divisor.reverseLinks();

        Node nodoActualDividendo = dividendoActual.head;

        // Mientras aún haya dígitos en el dividendo
        while (nodoActualDividendo != null) {
            // Tomar el siguiente bloque de dígitos del dividendo
            BigInt dividendoParcial = new BigInt(); 
            dividendoParcial.addToRightMost(nodoActualDividendo.value);

            // Determinar cuántas veces el divisor cabe en el dividendo parcial
            short count = 0;
            while (dividendoParcial.compareTo(divisor) >= 0) {
                dividendoParcial = dividendoParcial.sub(divisor);
                count++;
            }

            // Añadir ese número al cociente
            cociente.addToRightMost(count);

            // Avanzar al siguiente nodo del dividendo
            nodoActualDividendo = nodoActualDividendo.next;
        }

        // Revertir el orden de los nodos en el cociente
        cociente.reverseLinks();

        // Determinar el signo del cociente
        if (this.isNegative() == R.isNegative()) {
            if (cociente.isNegative()) {
                cociente.flipSign();
            }
        } else {
            if (!cociente.isNegative()) {
                cociente.flipSign();
            }
        }

        return cociente;
    }


   private void appendNode(Node node) {
       if (this.head == null) {
           this.head = node;
       } else {
           Node current = this.head;
           while (current.next != null) {
               current = current.next;
           }
           current.next = node;
       }
   }

    private void addToRightMost(short value) {
        // Agregar un nodo al extremo derecho (extremo menos significativo) del BigInt
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    public void incr(){ // EJERCICIO 15
        /*
        Increments in 1 the BigInt
        */
        boolean carry = true;
        Node p = head;
        
        // Caso negativo
        if(this.isNegative()){
            this.flipSign();
            this.decr();
            this.flipSign();
            return;
        }
 
        // Caso positivo
        do{
            if (p.value < Node.MAX_VALUE - 1){
                p.value++;
                carry = false; // Se deja de llevar el acarreo
            }
            else{   //Caso: el valor es 999
                if( p.next == null){ // Si no hay siguiente bloque, lo crea
                    p.next = new Node();
                }
                p.value = 0; // Llevamos el acarreo
                p = p.next;
                
            }
        }while(carry);
        
    }
    public void decr(){ // EJERCICIO 16
        /*
        Decrements in 1 the BigInt
        */
        Node p = head;
        Node prev = null; // Para mantener el nodo anterior
        
        // Caso negativo
        if(this.isNegative()){ 
            this.flipSign();
            this.incr();
            this.flipSign();
            return;
        }
        
        // Caso cero
        if (p.value == 0 && p.next == null){
            p.value = 1;
            this.flipSign(); //A negativo
            return;
        }
        
        // Caso positivo;
        while(true){
            if (p.value == 0){ // Caso con acarreo. Ej: 1,000 -> 1,999 (siguiente iteración lo lleva a 0,999)
                // Se resta al siguiente bloque
                p.value = Node.MAX_VALUE - 1;
                prev = p;
                p = p.next;
                // Se decrementará el siguiente bloque a en la siguiente iteración
            }
            else{ // Caso simple. Ej: 2,023 -> 2,022; 1,999 -> 0,999 si la anterior iteración se avanzó de nodo
                p.value--;
                if(p.value == 0 && p.next == null && prev != null){// Removemos el nodo. Ej: 0,999 -> 999
                    prev.next = null;
                }
                return;
            }
        }
        
    }
    
    public int compareTo(BigInt num){
        BigInt L = this;
        BigInt R = num;
        
        // Si tienen signos diferentes
        if (L.isPositive() && R.isNegative())
            return +1;
        if (L.isNegative() && R.isPositive())
            return -1;
        
        //Si tienen el mismo signo
        // Ojo: se usará una comparación por strings
        String Lstr = L.toString();
        String Rstr = R.toString();
        int result;
        int lengthL = Lstr.length();
        int lengthR = Rstr.length();
        
        //primero verifica la magnitud
        if(lengthL == lengthR)
            result = Lstr.compareTo(Rstr);
        else
            result = (lengthL < lengthR) ? -1 : +1;
        
        return L.sign * result;
        
    }
    
    @Override
    public String toString(){
        return this.toString("");
    }
    public String toString(String prefix){
        StringBuffer strBuf = new StringBuffer("");
        
        /*
        Digamos que tenemos el bloque de value = 7,
        si hay un bloque que le sigue (next diferente de null),
        se tiene que rellenar los ceros precedentes, esto es
        insertar la string "007" en el buffer
        */
        String format = "%0" + Node.MAX_DIGITS + "d";
        Node p = head;
        while (p.next != null){
            strBuf.insert(0, prefix + String.format(format, p.value)); //-> SOLUCION EJERCICIO 14
            p = p.next; // Se recorre el siguiente bloque 
        }
        strBuf.insert(0, p.value);  // Se agrega el bloque más significativo
                                           // No se requiere rellenar con ceros
                                           
        if(sign < 0){   // Agregamos el signo si es negativo
            strBuf.insert(0, "-");
        }
        
        return strBuf.toString();
    }
    
    private static String extractPrecedentZeros(String str){
        StringBuffer strBuf = new StringBuffer(str);
        int length = strBuf.length();
        for(int i = 0; i < length; i++){
            if (strBuf.charAt(0) == '0') // Eliminamos el primer cero
                strBuf.deleteCharAt(0);
        }
        if(strBuf.length() == 0)
            strBuf.append('0');
        return strBuf.toString();
    }
    
    private BigInt extractPrecedentZeros(){
        String numStr = this.toString();
        String result = extractPrecedentZeros(numStr);
        if(result.equals("0"))
            return new BigInt(0);
        else if (result.length() < numStr.length())
            return new BigInt(result);
        else    
            return this;
    }
    
    public static void main(String[] args) {
//        BigInt [] eg = new BigInt[7] ;
//        eg[0] = new BigInt ("12345678") ;
//        eg[1] = new BigInt ("-45");
//        eg[2] = new BigInt ("419284312218947");
//        eg[3] = new BigInt ("-000000000000000000000");
//        eg[4] = new BigInt (123456789) ;
//        eg[5] = new BigInt (-45);
//        eg[6] = new BigInt (777);
//        for (int i = 0; i < eg.length; i++) {
//            System.out.println(i + ":\t" + eg[i].toString());
//        }
//        
//        //Trying arithmetic operations:
//        BigInt bsum = eg[0].sum(eg[6]);
//        System.out.println(bsum.toString() + "\t" + (12345678 + 777));
        
        
        // Comparing against BigInteger
//        String[] strArray = {"-100005000",
//            "-91827347300072817",
//            "8000",
//            "3283748300000",
//            "-7",
//            "100005000",
//            "-2147483646",
//            "2147480000",
//            "-10000000000000000"
//        };
        String[] strArray = {"-1000",
            "-91827",
            "8000",
            "3284",
            "-7",
            "1005",
            "-214",
            "2170",
            "-1000",
            "-0000"
        };
        
        // ------ IMPRIMIMOS VALORES ----- //
        BigInt bi;
        for (int i = 0; i < strArray.length; i++) {
            bi = new BigInt(strArray[i]);
            System.out.println(i + ":\t" + bi.toString(","));
        }
        
        
        // ------ PROBANDO INC Y DEC ------- //
        
        System.out.println("\n\nProbando incremento y decremento:");
        System.out.println("Caso positivo:");
        System.out.printf("\tIncremento:");
        bi = new BigInt("999998");
        for (int i = 0; i < 4; i++) {
            System.out.printf(bi.toString() + ", ");
            bi.incr();
        }
        System.out.printf("\n\tDecremento:");        
        for (int i = 0; i < 4; i++) {
            System.out.printf(bi.toString() + ", ");
            bi.decr();
        }
        
        System.out.println("\nCaso negativo:");
        System.out.printf("\tIncremento:");
        bi = new BigInt("-1000001");
        for (int i = 0; i < 4; i++) {
            System.out.printf(bi.toString() + ", ");
            bi.incr();
        }
        System.out.printf("\n\tDecremento:");        
        for (int i = 0; i < 4; i++) {
            System.out.printf(bi.toString() + ", ");
            bi.decr();
        }
        
        System.out.println("\nCaso cero:");
        System.out.printf("\tIncremento:");
        bi = new BigInt("-2");
        for (int i = 0; i < 4; i++) {
            System.out.printf(bi.toString() + ", ");
            bi.incr();
        }
        bi = new BigInt("2");
        System.out.printf("\n\tDecremento:");        
        for (int i = 0; i < 4; i++) {
            System.out.printf(bi.toString() + ", ");
            bi.decr();
        }
        
        // ------- PROBANDO OPERACIONES ARITMÉTICAS ------- //
        
        BigInt e1;
        BigInt e2;
        BigInt e3;
        BigInteger bi1;
        BigInteger bi2;
        BigInteger bi3;
        
        int errorCount  = 0;
        
        for (int i = 0; i < strArray.length; i++) {
            for (int j = 0; j < strArray.length; j++) {   
                e1 = new BigInt(strArray[i]);
                e2 = new BigInt(strArray[j]);
                bi1 = new BigInteger(strArray[i]);
                bi2 = new BigInteger(strArray[j]);                
                
                // ------------ SUM AND SUB ------------ //
                System.out.println("\n// ------------ SUM AND SUB ------------ //");
                e3 = e1.sum(e2);
                bi3 = bi1.add(bi2);
                
                if(bi3.compareTo(new BigInteger(e3.toString())) != 0){
                    errorCount++;
                    System.out.printf("Sum failed ");
                    System.out.println("for the pair i = " + i + "\t j = " + j);
                    System.out.println("Numbers:\te1:" + e1.toString() + "\t" + "e2:" + e2.toString());
                    System.out.println("Expected " + bi3.toString() + "\t, got " + e3.toString() + "\n");
                }
                
                // ------------ MULT ------------ //
                System.out.println("// ------------ SIMPLE MULT ------------ //");
                e3 = e1.simpleMult(e2);
                bi3 = bi1.multiply(bi2);
                
                System.out.println("for the pair i = " + i + "\t j = " + j);
                System.out.println("Numbers:\te1:" + e1.toString() + "\t" + "e2:" + e2.toString());
                System.out.println("Expected " + bi3.toString() + "\t, got " + e3.toString() + "\n");
                if(bi3.compareTo(new BigInteger(e3.toString())) != 0){
                    errorCount++;
                    System.out.printf("Simple multiplication failed!!");
                }
                System.out.println("// ------------ MULT ------------ //");
                e3 = e1.mult(e2);
                bi3 = bi1.multiply(bi2);
                
                System.out.println("for the pair i = " + i + "\t j = " + j);
                System.out.println("Numbers:\te1:" + e1.toString() + "\t" + "e2:" + e2.toString());
                System.out.println("Expected " + bi3.toString() + "\t, got " + e3.toString() + "\n");
                if(bi3.compareTo(new BigInteger(e3.toString())) != 0){
                    errorCount++;
                    System.out.printf("Multiplication failed!!");
                }
                
                // ------------ DIV ------------ //
                System.out.println("// ------------ SIMPLE DIV ------------ //");
                try{ //PARTE 2 DEL EJERCICIO 19
                    e3 = e1.simpleDiv(e2);
                    bi3 = bi1.divide(bi2);
                }
                catch (ArithmeticException e){
                    System.out.println("Exception: " + e.getMessage());
                    continue;
                }
                
                System.out.println("for the pair i = " + i + "\t j = " + j);
                System.out.println("Numbers:\te1:" + e1.toString() + "\t" + "e2:" + e2.toString());
                System.out.println("Expected " + bi3.toString() + "\t, got " + e3.toString() + "\n");
                if(bi3.compareTo(new BigInteger(e3.toString())) != 0){
                    errorCount++;
                    System.out.printf("Simple division failed!!");
                }
//                System.out.println("// ------------ DIV ------------ //");
//                e3 = e1.div(e2);
//                bi3 = bi1.divide(bi2);
//                
//                System.out.println("for the pair i = " + i + "\t j = " + j);
//                System.out.println("Numbers:\te1:" + e1.toString() + "\t" + "e2:" + e2.toString());
//                System.out.println("Expected " + bi3.toString() + "\t, got " + e3.toString() + "\n");
//                if(bi3.compareTo(new BigInteger(e3.toString())) != 0){
//                    errorCount++;
//                    System.out.printf("Division failed!!");
//                }
            }
        }
        int totalOperations = (strArray.length * strArray.length) * 2;
        float successRate = 1 - ((float) errorCount ) / totalOperations;
        System.out.println("\n\nSuccess rate: %" + Float.toString(100 * successRate));
        System.out.println("Number of errors: " + Integer.toString(errorCount));
        
    }
}
