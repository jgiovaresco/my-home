import '../src/_core/_rwd.scss';
import '../src/_core/_colors.scss';
import '../src/_core/_typography.scss';

export const parameters = {
  actions: { argTypesRegex: '^on[A-Z].*' },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};
