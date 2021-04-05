import type { RootState } from '../_store';

export const selectStatus = (state: RootState) => state.library.books.status;
export const selectBooks = (state: RootState) =>
  state.library.books.result?.rows;
