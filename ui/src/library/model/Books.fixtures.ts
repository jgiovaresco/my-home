import type { Books } from './Books';
import { newBook } from './Book.fixtures';

export function newBooks(attr?: Partial<Books>): Books {
  return {
    rows: [newBook(), newBook(), newBook(), newBook(), newBook()],
    ...attr,
  };
}
