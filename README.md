# SistemaUniversitario
Los campus universitarios modernos son entornos complejos que combinan múltiples edificios distribuidos en un área geográfica, cada uno con su propia organización interna en pisos, aulas, laboratorios y oficinas.
Actualmente, muchas universidades ofrecen planos estáticos o señalización física que no resuelven el problema de forma integrada. Un estudiante puede saber dónde está el edificio de Ingeniería en un mapa general, pero no cómo llegar rápidamente al aula ING-501 dentro de ese edificio, ni cómo combinar ambas navegaciones si debe cambiar de edificio.

Esta problemática es representativa de un conjunto más amplio de situaciones del mundo real donde coexisten:
· Relaciones espaciales o de conectividad entre zonas (modelables mediante grafos).
· Relaciones jerárquicas o de contención dentro de cada zona (modelables mediante árboles).
Objetivo:
Desarrollar un sistema computacional en Java que, dado un campus universitario modelado como una integración de estructuras de datos no lineales, sea capaz de:
Calcular y mostrar la ruta completa más corta desde un aula de origen hasta un aula de destino, considerando:
1. La ruta óptima entre edificios del campus, basada en distancias reales (grafo ponderado).
2. La ruta interna dentro del edificio origen, desde el aula hasta la salida principal del edificio (árbol general).
3. La ruta interna dentro del edificio destino, desde la entrada principal hasta el aula objetivo (árbol general).
Adicionalmente, el sistema debe permitir:
· La búsqueda eficiente de cualquier aula por su código identificador mediante un índice optimizado (ABB).
· La gestión completa de edificios (agregar, eliminar, conectar).
· La gestión completa de aulas (insertar, buscar, modificar, eliminar).
