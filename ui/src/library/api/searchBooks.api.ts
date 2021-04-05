import { createAsyncThunk } from '@reduxjs/toolkit';
import type { Books } from '../model';
import { newBooks } from '../model';

type SearchBooksError = {
  message: string;
};

export const searchBooks = createAsyncThunk<
  Books,
  void,
  { rejectValue: SearchBooksError }
>('books/searchBooks', async () => {
  // call api
  return Promise.resolve(newBooks());
});
