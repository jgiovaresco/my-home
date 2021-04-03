import type { RootState } from '../_store';

export const getBooks = (state: RootState) => state.library.books;
