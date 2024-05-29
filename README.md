# Entrega parcial - Trabajo integrador - "El Aparato"

## Breve Descripción del Proyecto

"El Aparato" es una empresa de venta de electrodomésticos, opera con un sistema Java robusto diseñado para administrar productos y ventas a través de múltiples endpoints que facilitan operaciones CRUD en una base de datos MySQL. Esta infraestructura es fundamental para la gestión eficiente de las transacciones comerciales diarias y el inventario de productos; además de la consulta de usuarios. Aparte de la gestión operativa, se está empleando Keycloak como solución de IAM para mejorar la gestión de roles, permisos y autenticación. Esta evolución apunta a reforzar la seguridad y la eficiencia del sistema, permitiendo a la empresa adaptarse a las necesidades cambiantes y escalar de manera sostenible. La iniciativa de integrar Keycloak invita a explorar y realizar ajustes que optimicen el rendimiento y la seguridad del sistema existente. 

## Configuración de clientes, roles y usuarios

En mi realm "el-aparato-crocco" creé roles tanto a nivel de cliente como de reino.

### Creé en Keycloak 2 clientes:

- **users-client**
	1. En 'Capability config' seteamos Client authetication en 'On', en 'Authentication flow' marcamos 'Standard flow' y 
	'Service accounts roles'.
	2. En 'Service accounts roles' asignamos de 'realm-managements' query-users, view-users y manage-users.
	
- **api-gateway-client**
	1. En 'Account settings' configuramos la 'Root URL' y 'Valid redirect URIs' en "http://localhost:9090/login/oauth2/code/keycloak"
	2. En 'Capability config' seteamos 'Client authetication' en 'On', en 'Authentication flow' marcamos 'Standard flow' y 
	'Service accounts roles'.
	3. Asignamos al cliente los roles: *rol_administrador, rol_repositor y rol_vendedor*.

## En Realm Roles creé 3 roles:
	1. app_administrador relacionado con rol_administrador (rol de cliente).
	2. app_repositor relacionado con rol_repositor (rol de cliente).
	3. app_vendedor relacionado con rol_vendedor (rol de cliente).

### Aclaraciones 

- **Rol de Vendedor:** Podrá llevar a cabo operaciones CRUD de las ventas
a través de los endpoints destinados a las ventas.

- **Rol de Repositor:** Podrá llevar a cabo operaciones CRUD de los
productos a través de los endpoints destinados a los productos.

- **Rol de Administrador:** Podrá llevar a cabo tanto las operaciones del rol
de vendedor como de las de repositor en los endpoints de ambos casos. Además del acceso a la información de usuarios.

