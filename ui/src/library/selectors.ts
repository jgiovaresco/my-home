import type { RootState } from '../store';

export const getBooks = (state: RootState) => state.library.books;
