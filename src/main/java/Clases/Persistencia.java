package Clases;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.cfg.Configuration;



public class Persistencia {

	public static void insertarPersonal(String nombre, String tel, String dni, String horario, String cd) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		personal p = new personal();
		p.setDni_personal(dni);
		p.setNombre(nombre);
		p.setHorario(horario);
		p.setTelefono(Integer.parseInt(tel));
                
                p.setSucursal(entityManager.find(sucursales.class, cd));
		
		
		entityManager.persist(p);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}
	
	
	
	
	public static void modificarPersonal(String nombre, String tel, String dni, String horario, String cd) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		personal p = new personal();
		p.setDni_personal(dni);
		p.setNombre(nombre);
		p.setHorario(horario);
		p.setTelefono(Integer.parseInt(tel));
		
		p.setSucursal(entityManager.find(sucursales.class, cd));
		
		
		entityManager.merge(p);
		entityManager.getTransaction().commit();
		
		entityManager.close();
		factory.close();
	}
	
	
	public void eliminarPersonal(String dni) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		personal p = entityManager.find(personal.class, dni);
		entityManager.remove(p);
		entityManager.getTransaction().commit();
		
	}
	
	
	
	public ArrayList<personal> mostrarPersonal() {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT p FROM personal p";
		Query query = entityManager.createQuery(consulta);
		List<personal> lista = new ArrayList<personal>();
		personal p =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return (ArrayList<personal>) lista;
	}
        
//--------------------------------------------------------------------------------------------------------
	
        public static void insertarCliente(String nombre, String apellido, String dni, String tel, String precio) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		cliente c = new cliente();
		c.setNombre(nombre);
                c.setApellido(apellido);
                c.setDni(dni);
                c.setTelefono(tel);
                c.setPrecio(precio);
		
                entityManager.persist(c);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        public static void modificarCliente(String nombre, String apellido, String dni, String tel, String precio) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		cliente c = new cliente();
		c.setNombre(nombre);
                c.setApellido(apellido);
                c.setDni(dni);
                c.setTelefono(tel);
                c.setPrecio(precio);
		
                entityManager.merge(c);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
        
        public void eliminarCliente(String dni) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		cliente c = entityManager.find(cliente.class, dni);
		entityManager.remove(c);
		entityManager.getTransaction().commit();
		
	}
        
        public cliente mostrarCliente(String dni) {
            try{
             EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT c FROM cliente c WHERE c.dni = '"+dni+"'";
		Query query = entityManager.createQuery(consulta);
		List<cliente> lista = new ArrayList<cliente>();
		cliente c =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return lista.get(0);   
            }catch(Exception e){
                cliente cc= new cliente();
                cc.setDni("no");
                return cc;
            }
	}
        
        
//---------------------------------------------------------------------------------------------------------------        
        
        
	public static void insertarHabitacion(String num_hab, String dni_cli, String cp) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		habitaciones h = new habitaciones();
                h.setNumero_habitacion(num_hab);
                h.setSucursal(entityManager.find(sucursales.class, cp));
                h.setDni_Cliente(entityManager.find(cliente.class, dni_cli));
		
                entityManager.persist(h);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        public static void modificarHabitacion(int id, String num_hab, String dni_cli, String cp) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		habitaciones h = new habitaciones();
                h.setNumero_habitacion(num_hab);
                h.setSucursal(entityManager.find(sucursales.class, cp));
                h.setDni_Cliente(entityManager.find(cliente.class, dni_cli));
		
                entityManager.merge(h);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
	
        
        public static void eliminarHabitacion(int id) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		habitaciones h = entityManager.find(habitaciones.class, id);
		entityManager.remove(h);
		entityManager.getTransaction().commit();
		
	}
        
        public ArrayList<habitaciones> mostrarHabitaciones() {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT h FROM habitaciones h";
		Query query = entityManager.createQuery(consulta);
		List<habitaciones> lista = new ArrayList<habitaciones>();
		habitaciones h =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return (ArrayList<habitaciones>) lista;
	}
        
        public ArrayList<habitaciones> mostrarHabitacionesCliente(String dni) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT h FROM habitaciones h WHERE h.dni_cliente = '"+dni+"'";
		Query query = entityManager.createQuery(consulta);
		List<habitaciones> lista = new ArrayList<habitaciones>();
		habitaciones h =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return (ArrayList<habitaciones>) lista;
	}
        
        
        public static ArrayList<habitaciones> mostrarHabitacionesClienteHab(int id) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT h FROM habitaciones h WHERE h.id = '"+id+"'";
		Query query = entityManager.createQuery(consulta);
		List<habitaciones> lista = new ArrayList<habitaciones>();
		habitaciones h =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return (ArrayList<habitaciones>) lista;
	}
        
//-------------------------------------------------------------------------------------------------------------------        
        
        public static void insertarMantenimiento( String fecha, String redactar, String id_num) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		mantenimiento m = new mantenimiento();
                m.setFecha(fecha);
                m.setRedactar(redactar);
                m.setHabitacion(entityManager.find(habitaciones.class, id_num));
		
                entityManager.persist(m);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        
        public static void modificarMantenimiento( String fecha, String redactar, String id_num) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		mantenimiento m = new mantenimiento();
                m.setFecha(fecha);
                m.setRedactar(redactar);
                m.setHabitacion(entityManager.find(habitaciones.class, id_num));
		
                entityManager.merge(m);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        public void eliminarMantenimiento(String fecha) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		mantenimiento m = entityManager.find(mantenimiento.class, fecha);
		entityManager.remove(m);
		entityManager.getTransaction().commit();
		
	}
        
        public ArrayList<mantenimiento> mostrarMantenimiento() {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT m FROM mantenimiento m";
		Query query = entityManager.createQuery(consulta);
		List<mantenimiento> lista = new ArrayList<mantenimiento>();
		mantenimiento m =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return (ArrayList<mantenimiento>) lista;
	}
        
        
//--------------------------------------------------------------------------------------------------------------        
        
        public static void insertarReclamacion( String reclamacion, String dni_cli) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		reclamaciones r = new reclamaciones();
                r.setReclamacion(reclamacion);
                r.setCliente(entityManager.find(cliente.class, dni_cli));
		
                entityManager.persist(r);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        public static void modificarReclamacion( String reclamacion, String dni_cli) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		reclamaciones r = new reclamaciones();
                r.setReclamacion(reclamacion);
                r.setCliente(entityManager.find(cliente.class, dni_cli));
		
                entityManager.merge(r);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        public void eliminarReclamacion(int id) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		reclamaciones r = entityManager.find(reclamaciones.class, id);
		entityManager.remove(r);
		entityManager.getTransaction().commit();
		
	}
        
        public static ArrayList<reclamaciones> mostrarReclamacion() {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT r FROM reclamaciones r";
		Query query = entityManager.createQuery(consulta);
		List<reclamaciones> lista = new ArrayList<reclamaciones>();
		reclamaciones m =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return (ArrayList<reclamaciones>) lista;
	}
        
        
        public static ArrayList<reclamaciones> mostrarReclamacionPersona(String dni) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT r FROM reclamaciones r WHERE r.cliente = '"+dni+"'";
		Query query = entityManager.createQuery(consulta);
		List<reclamaciones> lista = new ArrayList<reclamaciones>();
		//reclamaciones m =null;
		lista = query.getResultList();
		
		entityManager.getTransaction().commit();
		return (ArrayList<reclamaciones>) lista;
	}
        
        
//------------------------------------------------------------------------------------------------------------------        
        
        public static void insertarSucursal( String ciudad, String cp, String nombre) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		sucursales s = new sucursales();
                s.setCiudad(ciudad);
                s.setCodigo_postal(ciudad);
                s.setNombre(nombre);
                s.setImagen("");
		
                entityManager.persist(s);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        public static void modificarSucursal( String ciudad, String cp, String nombre) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		sucursales s = new sucursales();
                s.setCiudad(ciudad);
                s.setCodigo_postal(ciudad);
                s.setNombre(nombre);
                s.setImagen("");
		
                entityManager.merge(s);
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
        
        
        public void eliminarSucursal(String cp) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		sucursales s = entityManager.find(sucursales.class, cp);
		entityManager.remove(s);
		entityManager.getTransaction().commit();
		
	}
        
        public static sucursales mostrarSucursal(String nombre) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT s FROM sucursales s WHERE s.nombre = '"+nombre+"'";
		Query query = entityManager.createQuery(consulta);
		List<sucursales> lista = new ArrayList<sucursales>();
		sucursales s =null;
		lista = query.getResultList();//Debes usar lista
		
		entityManager.getTransaction().commit();
		return lista.get(0);
	}
        
        public static sucursales mostrarSucursalCP(String cp) {
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		String consulta="SELECT s FROM sucursales s WHERE s.codigo_postal = '"+cp+"'";
		Query query = entityManager.createQuery(consulta);
		List<sucursales> lista = new ArrayList<sucursales>();
		sucursales s =null;
		lista = query.getResultList();//Debes usar lista
		
		entityManager.getTransaction().commit();
		return lista.get(0);
	}
        
        
        
//-----------------------------------||||||||||||||||||||||||||||------------------------------------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory= Persistence.createEntityManagerFactory("hotel");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		/*
		personal p = new personal();
		p.setNombre("Karim");
		p.setDni_personal("45123456T");
		p.setTelefono(633456534);
		p.setHorario("tarde");
		
		entityManager.persist(p);
		entityManager.getTransaction().commit();
		*/
		
		//añadirPersonal("ismael","633041898","45124234L","tarde","Malaga");
		//modificarPersonal("Jose","633041898","45124234L","tarde","Malaga");
		
                //insertarCliente("Ismael","Mohamed","45454545R","666666666","10");
                //insertarHabitacion("2","45454545R","51002");
                
                
                //sucursales s = mostrarSucursal("Hotel_1");
                //System.out.println(s.getCiudad());
                
                
                //insertarReclamacion("No limpian", "45124239X");
                //mostrarReclamacionPersona("45124239X");
                //mostrarReclamacion();
                //eliminarHabitacion(3);
                
                //modificarHabitacion(1, "4", "45454545R","51002");
                //mostrarHabitacionesClienteHab(2);
                //mostrarReclamacionPersona("45454545R");
                
                
		entityManager.close();
		factory.close();
		
	}
}
