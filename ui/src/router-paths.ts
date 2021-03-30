import { BooksRoute, HomeRoute } from './routes';

const pathsMap = {
  home: () => '/',
  books: () => '/books',
};
type PathsMap = typeof pathsMap;

export const getPath = <TRoute extends keyof PathsMap>(
  route: TRoute,
  ...params: Parameters<PathsMap[TRoute]>
) => {
  const pathCb: (...args: any[]) => string = pathsMap[route];

  return pathCb(...params);
};

export const routes = [
  {
    path: getPath('home'),
    label: 'Home',
    component: HomeRoute,
  },
  {
    path: getPath('books'),
    label: 'Books',
    component: BooksRoute,
  },
];
