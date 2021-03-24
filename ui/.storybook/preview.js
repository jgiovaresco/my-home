import '../src/core/_rwd.scss';
import '../src/core/_colors.scss';
import '../src/core/_typography.scss';

export const parameters = {
  actions: { argTypesRegex: '^on[A-Z].*' },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};
