import { combineReducers } from 'redux';
import { booksSlice } from './book.slice';

export const libraryReducer = combineReducers({
  books: booksSlice.reducer,
});
