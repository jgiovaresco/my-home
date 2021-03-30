import { createSlice } from '@reduxjs/toolkit';

import { Book, newBook } from './model';

const initialState: Book[] = [];

export const booksSlice = createSlice({
  name: 'books',
  initialState,
  reducers: {
    searchBook: () => [newBook(), newBook(), newBook(), newBook()],
  },
});

export const { searchBook } = booksSlice.actions;
