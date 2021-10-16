import { BooksRoute } from './BooksRoute';

export const booksRoute = {
  path: '/books',
  label: 'Books',
  component: BooksRoute,
  secured: true,
};
