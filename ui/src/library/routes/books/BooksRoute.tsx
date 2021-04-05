import React, { useEffect } from 'react';

import { useAppDispatch, useAppSelector } from '../../../_store';
import { searchBooks } from '../../api';
import { selectBooks, selectStatus } from '../../selectors';
import { BooksList } from '../../components';

export function BooksRoute() {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(searchBooks());
  }, []);

  const books = useAppSelector(selectBooks);
  const status = useAppSelector(selectStatus);

  return status === 'loading' ? <p>Loading...</p> : <BooksList books={books} />;
}
