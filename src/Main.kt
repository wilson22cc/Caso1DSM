open class Persona(
    val nombre: String,
    val apellidos: String,
    val fechaNacimiento: String,
    val sexo: String
)

class Empleado(
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    sexo: String,
    val sueldoBruto: Int
) :Persona(nombre, apellidos, fechaNacimiento, sexo){
    val subordinados: MutableList<Empleado> = mutableListOf()

    fun anadirSubordinado(subordinado: Empleado){
        subordinados.add(subordinado)
    }
}

class Cliente(
    nombre: String,
    apellidos: String,
    fechaNacimiento: String,
    sexo: String,
    val telefono: Int
): Persona(nombre, apellidos, fechaNacimiento, sexo)

class Empresa(
    val nombre: String
){
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

fun main() {
    val empleado1 = Empleado("Jhosselin","Clemente","","F",4000)
    val empleado2 = Empleado("Joaquin","Del Aguila","","M",3500)
    val empleado3 = Empleado("Jesus","Vasquez","400 aC","M",1200)

    val cliente1 = Cliente("Wilson","Leyva","2003","M",938266816)
    val cliente2 = Cliente("Fiorella","Perez","","F",987654321)
    val cliente3 = Cliente("Ricardo","Chuquivilca","","M",963852741)

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