import { createSlice } from '@reduxjs/toolkit';

import { searchBooks } from './api';
import type { Books } from './model';

type BooksState = {
  status: 'loading' | 'idle' | 'succeeded' | 'failed';
  result?: Books;
  error: string | null;
};

const initialState: BooksState = {
  status: 'idle',
  error: null,
};

export const booksSlice = createSlice({
  name: 'books',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(searchBooks.pending, (state) => {
      state.status = 'loading';
      state.error = null;
    });

    builder.addCase(searchBooks.fulfilled, (state, { payload }) => {
      state.status = 'succeeded';
      state.result = payload;
    });

    builder.addCase(searchBooks.rejected, (state, { payload }) => {
      state.status = 'failed';
      if (payload) {
        state.error = payload.message;
      }
    });
  },
});
