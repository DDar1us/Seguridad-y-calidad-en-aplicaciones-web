package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import ar.edu.unlam.diit.scaw.services.impl.UsuarioServiceImpl;

@ManagedBean(name = "usuarioBean", eager = true)
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String eMail = null;
	private String contraseña = null;
	private Integer id = null;
	private String apellido = null;
	private String nombre = null;
	private Integer idEstadoUsuario = null;
	private String descripcion = null;
	private Integer idRol = null;
	private String rolDescripcion = null;
	
	UsuarioService service;
	
	public UsuarioBean() {
		super();
		service = (UsuarioService) new UsuarioServiceImpl();
	}
	
	public UsuarioBean(String eMail, String contraseña, Integer id, String apellido, String nombre, Integer idEstadoUsuario, String descripcion, Integer idRol, String rolDescripcion) {
		super();
		this.eMail = eMail;
		this.contraseña = contraseña;
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.idEstadoUsuario = idEstadoUsuario;
		this.descripcion = descripcion;
		this.idEstadoUsuario = idEstadoUsuario;
		this.descripcion = descripcion;
		this.idRol = idRol;
		this.rolDescripcion = rolDescripcion;
	}
	
	public String save() {
		
		Usuario person = buildUsuario();
		
		service.save(person);
		
		return "welcome";
	}
	
	//Listado de todos los usuarios
	public List<Usuario> getFindAll() {
		List<Usuario> list = service.findAll();
		return list;
	}
	
	public String login(){
		
		Usuario usuario = new Usuario();
		usuario.setEmail(this.eMail);
		usuario.setContraseña(this.contraseña);	
		usuario.setIdEstadoUsuario(this.idEstadoUsuario);
		usuario.setDescripcion(this.descripcion);
		usuario.setIdRol(this.idRol);
		usuario.setRolDescripcion(this.rolDescripcion);	
		
		Usuario logueado = service.login(usuario);		
		if(logueado!=null && logueado.getIdEstadoUsuario().equals(2)) 
		{
			//admin
			if(logueado.getIdRol().equals(1)) 
			{
				return "welcome";
			}	
			//docentes
			else if(logueado.getIdRol().equals(2)) 
			{
				return "welcomeDocente";
			}
			//alumnos
			else if(logueado.getIdRol().equals(3)) 
			{
				return "welcomeAlumno";
			}
			else
			{
				return "error";
			}
		}
		else
		{
			return "error";
		}
		
	}

	private Usuario buildUsuario() {
		Usuario person = new Usuario();
		
		person.setEmail(this.eMail);
		person.setContraseña(contraseña);
		person.setId(this.id);
		person.setApellido(this.apellido);
		person.setNombre(this.nombre);
		person.setIdEstadoUsuario(this.idEstadoUsuario);
		person.setDescripcion(this.descripcion);
		person.setIdRol(this.idRol);
		
		return person;
	}
	
	//Listado de los usuarios pendientes de habilitacion
	public List<Usuario> getPendientes() {
		List<Usuario> list = service.pendientes();
		return list;
	}
	
	//Aceptar pendiente
	public String aceptar() {
			
			Usuario person = buildUsuario();
			
			service.aceptar(person);
			
			return "welcome";
		}

	public String getEmail() {
		return eMail;
	}

	public void setEmail(String email) {
		this.eMail = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}
	
	public Integer getIdEstadoUsuario() {
		return idEstadoUsuario;
	}

	public void setIdEstadoUsuario(Integer idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
