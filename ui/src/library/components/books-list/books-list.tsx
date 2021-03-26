import React from 'react';
import type { Book } from '../../model';
import { BookThumbnail } from '../book-thumbnail';
import './books-list.scss';

export interface BooksListProps {
  books?: Book[];
}

export const BooksList: React.FC<BooksListProps> = (props) => {
  const books = props.books ?? [];

  return (
    <section className="books-list">
      {books.map((b) => (
        <div key={b.isbn}>
          <BookThumbnail book={b} />
        </div>
      ))}
    </section>
  );
};
