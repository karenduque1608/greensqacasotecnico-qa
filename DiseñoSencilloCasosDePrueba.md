# Casos de Prueba - Módulo de Búsqueda de Vuelos LATAM

## Objetivo

Diseñar casos de prueba funcionales para validar el módulo de búsqueda de vuelos del sitio web de LATAM, cubriendo el flujo principal y escenarios de validación.

---

# Caso de Prueba CP-001

## Nombre
Búsqueda exitosa de un vuelo de ida y regreso.

### Objetivo
Validar que un usuario pueda realizar correctamente la búsqueda de un vuelo ingresando un origen, un destino y fechas válidas.

### Precondiciones
- El usuario se encuentra en la página principal de LATAM.
- El sitio web está disponible.

### Datos de prueba

| Campo | Valor |
|--------|-------|
| Origen | Cali |
| Destino | La Habana |
| Fecha de ida | 07/03/2027 |
| Fecha de regreso | 14/03/2027 |

### Pasos

1. Ingresar al sitio web de LATAM.
2. Seleccionar la ciudad de origen **Cali**.
3. Seleccionar la ciudad de destino **La Habana**.
4. Seleccionar una fecha de ida.
5. Seleccionar una fecha de regreso.
6. Hacer clic en el botón **Buscar vuelos**.

### Resultado esperado

- El sistema realiza la búsqueda correctamente.
- Se muestran los vuelos disponibles para la ruta seleccionada.
- La página de resultados contiene información de vuelos para las fechas ingresadas.

---

# Caso de Prueba CP-002

## Nombre
Validación cuando el origen y el destino son la misma ciudad.

### Objetivo
Verificar que el sistema no permita realizar una búsqueda cuando el origen y el destino corresponden a la misma ciudad.

### Precondiciones
- El usuario se encuentra en la página principal de LATAM.

### Datos de prueba

| Campo | Valor |
|--------|-------|
| Origen | Cali |
| Destino | Cali |
| Fecha de ida | 07/03/2027 |
| Fecha de regreso | 14/03/2027 |

### Pasos

1. Ingresar al sitio web de LATAM.
2. Seleccionar **Cali** como ciudad de origen.
3. Seleccionar **Cali** como ciudad de destino.
4. Seleccionar una fecha de ida.
5. Seleccionar una fecha de regreso.
6. Hacer clic en **Buscar vuelos**.

### Resultado esperado

- El sistema no permite realizar la búsqueda.
- Se muestra un mensaje indicando que el origen y el destino deben ser diferentes o se impide continuar con la búsqueda.

---

# Caso de Prueba CP-003

## Nombre
Validación de selección obligatoria de fechas.

### Objetivo
Verificar que el sistema solicite seleccionar las fechas del viaje antes de permitir la búsqueda de vuelos.

### Precondiciones
- El usuario se encuentra en la página principal de LATAM.

### Datos de prueba

| Campo | Valor |
|--------|-------|
| Origen | Cali |
| Destino | La Habana |
| Fecha de ida | No seleccionada |
| Fecha de regreso | No seleccionada |

### Pasos

1. Ingresar al sitio web de LATAM.
2. Seleccionar la ciudad de origen **Cali**.
3. Seleccionar la ciudad de destino **La Habana**.
4. No seleccionar las fechas del viaje.
5. Hacer clic en **Buscar vuelos**.

### Resultado esperado

- El sistema no ejecuta la búsqueda.
- Se solicita al usuario seleccionar las fechas del viaje antes de continuar.

---

# Cobertura de los casos de prueba

| ID | Escenario | Tipo |
|----|-----------|------|
| CP-001 | Búsqueda exitosa de vuelo | Flujo principal |
| CP-002 | Origen y destino iguales | Validación de negocio |
| CP-003 | Fechas obligatorias | Validación funcional |