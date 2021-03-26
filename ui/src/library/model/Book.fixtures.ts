import faker from 'faker';
import type { Book } from './Book';

export function newBook(attr?: Partial<Book>): Book {
  return {
    isbn: `978-${faker.random.number().toString().padStart(9, '0')}-2`,
    title: faker.random.words(),
    author: `${faker.name.firstName()} ${faker.name.lastName()}`,
    ...attr,
  };
}
