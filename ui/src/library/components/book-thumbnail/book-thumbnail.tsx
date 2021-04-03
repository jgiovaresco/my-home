import React from 'react';
import type { Book } from '../../model';
import './book-thumbnail.scss';
import BookOpenIcon from '../../../_core/assets/icons/icon-book-open.svg';

export interface BookThumbnailProps {
  book: Book;
}

export const BookThumbnail: React.FC<BookThumbnailProps> = ({ book }) => (
  <div className="book-thumbnail">
    <BookOpenIcon />
    <span className="title">{book.title}</span>
    <span className="author">{book.author}</span>
  </div>
);
