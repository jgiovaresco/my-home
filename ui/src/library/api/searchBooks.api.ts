import { createAsyncThunk } from '@reduxjs/toolkit';
import type { Books } from '../model';
import { getAxiosInstance } from '../../_api';

type SearchBooksError = {
  message: string;
};

export const searchBooks = createAsyncThunk<
  Books,
  void,
  { rejectValue: SearchBooksError }
>('books/searchBooks', async () => {
  const instance = await getAxiosInstance();
  return instance.get<Books>('books').then((response) => response.data);
});
