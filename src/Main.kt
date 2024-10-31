import java.lang.reflect.Constructor

open class Persona(
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    sexo: Gender
)
{
    var nombre: String = "-"
    var apellidos: String = "-"
    var fechaNacimiento: String = "ND"
    var sexo: Gender = Gender.ND

    init {
        this.nombre = nombre
        this.apellidos = apellidos
        this.fechaNacimiento = fechaNacimiento
        this.sexo = sexo
    }
}

class Empleado:Persona{
    var sueldoBruto: Int = 0
    val subordinados: MutableList<Empleado> = mutableListOf()

    constructor(nombre: String,apellidos: String,fechaNacimiento: String,sexo: Gender)
            : super(nombre,apellidos,fechaNacimiento,sexo)
    constructor(nombre: String,apellidos: String,fechaNacimiento: String,sexo: Gender,sueldoBruto: Int)
            : super(nombre, apellidos, fechaNacimiento,sexo) {
                this.sueldoBruto = sueldoBruto
        }

    fun anadirSubordinado(subordinado: Empleado){
        subordinados.add(subordinado)
    }
}

class Cliente:Persona{
    var telefono: String = "000000000"

    constructor(nombre: String,apellidos: String,fechaNacimiento: String,sexo: Gender)
            : super(nombre,apellidos,fechaNacimiento,sexo)
    constructor(nombre: String,apellidos: String,fechaNacimiento: String,sexo: Gender,telefono: String)
            : super(nombre,apellidos,fechaNacimiento,sexo){
                this.telefono = telefono
            }
}

class Empresa(
    nombre: String
)
{
    var nombre: String = "-"
    val empleados: MutableList<Empleado> = mutableListOf()
    val clientes: MutableList<Cliente> = mutableListOf()

    fun agregarEmpleado(empleado: Empleado){
        empleados.add(empleado)
    }

    fun agregarCliente(cliente: Cliente){
        clientes.add(cliente)
    }

    fun mostrarDatos(){
        println("Empleados:")
        empleados.forEach { empleado ->
            println("${empleado.nombre} ${empleado.apellidos} - Sueldo bruto: ${empleado.sueldoBruto}")
            if (empleado.subordinados.isNotEmpty()){
                println("Subordinados:")
                empleado.subordinados.forEach { subordinado ->
                    println("\t${subordinado.nombre} ${subordinado.apellidos}")
                }
            }
        }
        println("\nClientes:")
        clientes.forEach { cliente->
            println("${cliente.nombre} ${cliente.apellidos} - Telefono: ${cliente.telefono}")
        }
    }
}

enum class Gender {
    MASCULINO,FEMENINO,ND
}

fun main() {
    val empleado1 = Empleado("Jhosselin","Clemente","",Gender.FEMENINO,4000)
    val empleado2 = Empleado("Joaquin","Del Aguila","",Gender.MASCULINO,3500)
    val empleado3 = Empleado("Jesus","Vasquez","400 aC",Gender.MASCULINO,1200)

    val cliente1 = Cliente("Wilson","Leyva","2003",Gender.MASCULINO,"938266816")
    val cliente2 = Cliente("Fiorella","Perez","",Gender.FEMENINO,"987654321")
    val cliente3 = Cliente("Ricardo","Chuquivilca","",Gender.MASCULINO,"963852741")

    val empresa = Empresa("Tech Solutions")

    empleado1.anadirSubordinado(empleado2)
    empleado1.anadirSubordinado(empleado3)
    empresa.agregarEmpleado(empleado1)
    empresa.agregarEmpleado(empleado2)
    empresa.agregarEmpleado(empleado3)

    empresa.agregarCliente(cliente1)
    empresa.agregarCliente(cliente2)
    empresa.agregarCliente(cliente3)

    empresa.mostrarDatos()
}