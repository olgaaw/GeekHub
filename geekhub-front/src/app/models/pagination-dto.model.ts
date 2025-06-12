export interface PaginationDto<T> {
  contenido: T[];
  numPagina: number;
  tamanioPagina: number;
  elementosEncontrados: number;
  paginasTotales: number;
}
